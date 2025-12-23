package com.financeapp.finance.dto;

import java.math.BigDecimal;

import com.financeapp.finance.model.AccountType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private Long userId;
    private BigDecimal balance;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Account Type is required")
    private AccountType accountType;
    private String nickname;
    private BigDecimal interestRate;
    private BigDecimal minimumPayment;
    @NotNull(message = "Due Day is required")
    private Integer dueDay;
}
