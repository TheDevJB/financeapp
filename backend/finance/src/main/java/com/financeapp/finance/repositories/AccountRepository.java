package com.financeapp.finance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.AccountType;
import com.financeapp.finance.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Optional<Account> findByAccountId(Integer accountId);
    Optional<Account> findByUserOrAccountType(User user, AccountType accountType);
}
