package com.financeapp.finance.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User registerUser(String username, String email, String rawPassword, String phone) {
        Optional<User> findByUsername = userRepository.findByUsername(username);
        if (findByUsername.isPresent()){
            throw new IllegalArgumentException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(hashedPassword);

        return userRepository.save(user);

    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserDoesNotExistException("User not found"));
    }
}
