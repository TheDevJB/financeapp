package com.financeapp.finance.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financeapp.finance.exceptions.EmailDoesNotExistException;
import com.financeapp.finance.exceptions.UserDoesNotExistException;
import com.financeapp.finance.models.User;
import com.financeapp.finance.repositories.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("Username not found"));
    }

    public boolean authenticateUser(String username, String rawPassword){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("User not found"));
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new EmailDoesNotExistException("Email not found"));
    }
}
