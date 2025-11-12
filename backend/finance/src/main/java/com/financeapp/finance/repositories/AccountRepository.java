package com.financeapp.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

    Optional<Account> findByAccountId(String accountId);
    Optional<Account> findByUserOrAccountType(User user, String accountType);
}
