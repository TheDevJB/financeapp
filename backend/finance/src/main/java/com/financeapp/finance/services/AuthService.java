package com.financeapp.finance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financeapp.finance.exceptions.UserDoesNotExistException;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repository.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
    }

    public User getUserById(Integer userId){
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistException::new);
    } 

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public boolean authenticateUser(String username, String rawPassword, String storedHashedPassword){
        return passwordEncoder.matches(rawPassword, storedHashedPassword);
    }

}
