package com.studentmanagement.studentmanagement.controller;

import com.studentmanagement.studentmanagement.model.User;
import com.studentmanagement.studentmanagement.repository.UserRepo;
import com.studentmanagement.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public User signIn(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userService.signIn(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
       return userService.verify(user);
    }
}
