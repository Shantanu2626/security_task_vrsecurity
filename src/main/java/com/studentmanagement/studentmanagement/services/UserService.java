package com.studentmanagement.studentmanagement.services;

import com.studentmanagement.studentmanagement.model.User;
import com.studentmanagement.studentmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
   private JwtService jwtService;

    public User signIn(User user){
      return  userRepo.save(user);
    }


    //Authenticate uesr for /login if user is authenticated then token is generated
    public String verify(User user) {
     Authentication authenticate =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

     if(authenticate.isAuthenticated()){
         return jwtService.generateToken(user);
//         return "gklsfjkljjf";
     }
        return "failure";

    }




}
