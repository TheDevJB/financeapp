package com.financeapp.finance.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.financeapp.finance.model.TransactionType;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long transactionId;
    private Long userId;
    private BigDecimal dollarAmount;
    private String description;
    private Long accountId;
    private TransactionType transactionType;
    private Long categoryId;
    private Instant transactionDate;
}
