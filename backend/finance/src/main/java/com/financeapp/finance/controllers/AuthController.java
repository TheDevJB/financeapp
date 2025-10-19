package com.financeapp.finance.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeapp.finance.dto.FindUsernameDTO;
import com.financeapp.finance.dto.UserRegisterDTO;
import com.financeapp.finance.exceptions.UserDoesNotExistException;
import com.financeapp.finance.models.User;
import com.financeapp.finance.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    
    public AuthController(UserService userService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        return userService.registerUser(userRegisterDTO);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist(){
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("The user you are looking for does not exist");
    }

    @PostMapping("/search")
    public ResponseEntity<String> verifyUsername(@RequestBody FindUsernameDTO cred){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        String username = userService.verifyUsername(cred);
        return new ResponseEntity<>(username, HttpStatus.OK);
    }
}