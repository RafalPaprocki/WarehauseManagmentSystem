package com.warehausesystem.app.warehauseSystemWeb.controller;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.warehausesystem.app.warehauseSystemWeb.Exceptions.ResourceNotFoundException;
import com.warehausesystem.app.warehauseSystemWeb.message.request.ChangePasswordForm;
import com.warehausesystem.app.warehauseSystemWeb.message.request.LoginForm;
import com.warehausesystem.app.warehauseSystemWeb.message.request.SignUpForm;
import com.warehausesystem.app.warehauseSystemWeb.message.response.JwtResponse;
import com.warehausesystem.app.warehauseSystemWeb.message.response.ResponseMessage;
import com.warehausesystem.app.warehauseSystemWeb.model.Role;
import com.warehausesystem.app.warehauseSystemWeb.model.RoleName;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import com.warehausesystem.app.warehauseSystemWeb.repository.RoleRepository;
import com.warehausesystem.app.warehauseSystemWeb.repository.UserRepository;
import com.warehausesystem.app.warehauseSystemWeb.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/change/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePassword){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(changePassword.getUsername(), changePassword.getOldPassword()));
        userRepository.findByUsername(changePassword.getUsername()).map(employee -> {
            employee.setPassword(encoder.encode(changePassword.getNewPassword()));
            return userRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("User o id " + " not found"));
        return new ResponseEntity<>(new ResponseMessage("Rejestracja zakończona sukcesem"), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Taka nazwa użytkownika już istnieje!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Taki adres email już istnieje!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(),signUpRequest.getSurname(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getAgreement(),signUpRequest.getPosition());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Nieznaleziono roli admin"));
                    roles.add(adminRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Nie znaleziono roli użytkownik"));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Rejestracja zakończona sukcesem"), HttpStatus.OK);
    }
}