package com.stackoverflow.controllers;

import com.stackoverflow.dtos.SignupDTO;
import com.stackoverflow.dtos.UserDTO;
import com.stackoverflow.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> signupUser(@RequestBody(required = true) SignupDTO signupDTO){

        if(userService.hasUserWithEmail(signupDTO.getEmail())){
            return new ResponseEntity<>("User already exist with this " + signupDTO.getEmail(), HttpStatus.NOT_ACCEPTABLE);
        }else{
            UserDTO createdUser = userService.createUser(signupDTO);
            if (createdUser == null){
                return new ResponseEntity<>("User not Crated, come again later.", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            }
        }

    }
}
