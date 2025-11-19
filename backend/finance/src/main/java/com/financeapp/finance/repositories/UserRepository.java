package com.financeapp.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer userId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmailOrPhone(String email, String username, String phone);
}
