package com.financeapp.finance.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferDTO {
    private Long sourceAccountId;
    private Long destAccountId;
    private BigDecimal amount;
    private String description;
}
