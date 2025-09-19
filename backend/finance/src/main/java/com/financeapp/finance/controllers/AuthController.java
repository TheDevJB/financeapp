package com.financeapp.finance.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeapp.finance.dto.RegisterRequest;
import com.financeapp.finance.models.User;
import com.financeapp.finance.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request){
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword(), request.getPhone());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}