package com.financeapp.finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financeapp.finance.model.Transaction;
import com.financeapp.finance.service.TransactionService;
import com.financeapp.finance.dto.TransferDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}/transaction/{transactionId}")
    public Transaction getTransactionByTransactionId(@PathVariable Integer userId, @PathVariable Long transactionId) {
        return transactionService.getTransactionByTransactionId(transactionId);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferDTO> transferFunds(@Valid @RequestBody TransferDTO transferDTO) {
        Transaction transfer = transactionService.transfer(transferDTO);
        return ResponseEntity.ok(mapToDTO(transfer));
    }

    private TransferDTO mapToDTO(Transaction transaction) {
        TransferDTO dto = new TransferDTO();
        dto.setAmount(transaction.getDollarAmount());
        dto.setDescription(transaction.getDescription());
        return dto;
    }

}
