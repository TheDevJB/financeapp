package com.financeapp.finance.service;

import org.springframework.stereotype.Service;

import com.financeapp.finance.exception.UserDoesNotExistException;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User getUserById(Integer userId){
        return userRepo.findById(userId).orElseThrow(UserDoesNotExistException::new);
    }
}
