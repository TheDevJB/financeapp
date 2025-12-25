package com.financeapp.finance.service;

import java.util.List;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.financeapp.finance.dto.AccountDTO;
import com.financeapp.finance.exception.AccountDoesNotExistException;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.AccountType;
import com.financeapp.finance.model.User;
import com.financeapp.finance.repositories.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccountById(Long accountId) {
        return accountRepo.findByAccountId(accountId).orElseThrow(AccountDoesNotExistException::new);
    }

    public Account getAccountByUserAndAccountType(User user, AccountType accountType) {
        return accountRepo.findByUserOrAccountType(user, accountType).orElseThrow(AccountDoesNotExistException::new);
    }

    public List<Account> getAllAccountsByUser(User user) {
        return accountRepo.findAllAccountsByUser(user);
    }

    public Account createAccount(Long accountId, User user, AccountType accountType, BigDecimal amount,
            BigDecimal balance, AccountDTO dto) {
        Account newAccount = new Account();

        newAccount.setAccountId(accountId);
        newAccount.setUser(user);
        newAccount.setAccountType(accountType);
        newAccount.setAmount(amount);
        newAccount.setBalance(balance);
        newAccount.setNickname(dto.getNickname());
        newAccount.setInterestRate(dto.getInterestRate());
        newAccount.setDueDay(dto.getDueDay());

        accountRepo.save(newAccount);

        LOGGER.info("Account {} created successfully", accountId);

        return newAccount;
    }

    public void validateAccountType(String accountTypeString) {
        try {
            AccountType type = AccountType.valueOf(accountTypeString.toUpperCase());
            LOGGER.info("Valid account type: " + type);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Invalid account type: " + accountTypeString);
            throw new AccountDoesNotExistException("This account type is not valid");
        }
    }

    public Account updateAccountBalance(Long accountId, AccountType accountType, BigDecimal amount,
            BigDecimal balance) {
        Account account = getAccountById(accountId);

        if (accountType != account.getAccountType()) {
            throw new AccountDoesNotExistException("This account type is not valid");
        }

        account.setAccountType(accountType);
        account.setAmount(amount);
        account.setBalance(balance);
        accountRepo.save(account);

        LOGGER.info("Account balance updated");

        return account;
    }

    public List<Account> getAllAccountsByUserId(Long userId) {
        return accountRepo.findAllByUser_UserId(userId);
    }
}
