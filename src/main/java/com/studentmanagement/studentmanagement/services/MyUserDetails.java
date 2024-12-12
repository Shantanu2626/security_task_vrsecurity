package com.studentmanagement.studentmanagement.services;

import com.studentmanagement.studentmanagement.model.User;
import com.studentmanagement.studentmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;  // Repository for User entity

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        // Getting user from the database and creating User for Spring Security
        if (user.isPresent()) {
            var userObj = user.get();
            // Build and return the User object for Spring Security
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())  // Use the role from User entity
//                    .authorities(new SimpleGrantedAuthority("ROLE_" + userObj.getRole()))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
