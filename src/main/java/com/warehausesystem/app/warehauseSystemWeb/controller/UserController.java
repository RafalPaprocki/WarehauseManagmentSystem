package com.warehausesystem.app.warehauseSystemWeb.controller;

import com.warehausesystem.app.warehauseSystemWeb.model.User;
import com.warehausesystem.app.warehauseSystemWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
}
