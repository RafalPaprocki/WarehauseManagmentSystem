package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.Exceptions.ResourceNotFoundException;
import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.EmployeeFilter;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import com.warehausesystem.app.warehauseSystemWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("api/user/{username}")
    User getUserInfoByUsername(@PathVariable String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        return user;
    }

    @PostMapping("api/user/filtr")
    List<User> getUsersFiltered(@Valid @RequestBody EmployeeFilter employeeFilters){
        return userRepository.findUserFiltered(employeeFilters.name,
                employeeFilters.surname, employeeFilters.id);
    }

    @PutMapping("api/user/update")
    User updateUser(@Valid @RequestBody User user){
        return userRepository.findById(user.getId()).map(employee -> {
            employee.setUsername(user.getUsername());
            employee.setName(user.getName());
            employee.setEmail(user.getEmail());
            employee.setAgreement(user.getAgreement());
            employee.setPosition(user.getPosition());
            return userRepository.save(employee);
        }).orElseThrow(() -> new ResourceNotFoundException("Artykuł o id " + user.getId() + " not found"));
    }
}
