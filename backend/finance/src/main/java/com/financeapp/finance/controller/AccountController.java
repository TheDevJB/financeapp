package com.financeapp.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeapp.finance.dto.AccountDTO;
import com.financeapp.finance.model.Account;
import com.financeapp.finance.model.User;
import com.financeapp.finance.service.AccountService;
import com.financeapp.finance.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        User user = userService.getUserById(accountDTO.getUserId());

        Account createdAccount = accountService.createAccount(
                accountDTO.getAccountId(),
                user,
                accountDTO.getAccountType(),
                accountDTO.getAmount(),
                accountDTO.getBalance());

        return new ResponseEntity<>(mapToDTO(createdAccount), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(mapToDTO(account));
    }

    private AccountDTO mapToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountId(account.getAccountId());
        dto.setUserId(account.getUser().getUserId());
        dto.setBalance(account.getBalance());
        dto.setAmount(account.getAmount());
        dto.setAccountType(account.getAccountType());
        dto.setNickname(account.getNickname());
        dto.setInterestRate(account.getInterestRate());
        dto.setMinimumPayment(account.getMinimumPayment());
        dto.setDueDay(account.getDueDay());
        dto.setDebtAccount(account.getDebtAccount());
        return dto;
    }
}
