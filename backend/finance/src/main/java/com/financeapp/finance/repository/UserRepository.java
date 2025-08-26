package com.financeapp.finance.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;   
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Integer userId);
}
