package com.financeapp.finance.services;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByUsername(Integer userId){
        return userRepository.findById(userId).orElse(null);
    } 

}
