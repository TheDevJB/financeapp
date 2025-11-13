package com.financeapp.finance.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financeapp.finance.exception.EmailDoesNotExistException;
import com.financeapp.finance.exception.UserDoesNotExistException;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(Integer userId){
        return userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(EmailDoesNotExistException::new);
    }

    public User setPassword(String username, String password){
        User user = userRepo.findByUsername(username).orElseThrow(UserDoesNotExistException::new);

        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }


}
