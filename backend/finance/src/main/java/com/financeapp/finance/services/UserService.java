package com.financeapp.finance.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financeapp.finance.dto.UserRegisterDTO;
import com.financeapp.finance.exceptions.UserDoesNotExistException;
import com.financeapp.finance.models.User;
import com.financeapp.finance.repositories.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User registerUser(UserRegisterDTO registerDTO) {
        if(userRepository.findByEmail(registerDTO.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with this email already exists");
        }

        if(userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            throw new IllegalArgumentException("User with this username already exists");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());

        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("User not found"));
    }

    public User setPassword(String username, String password){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("User not found"));

        String encodedPassword = passwordEncoder.encode(password);
        
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

}
