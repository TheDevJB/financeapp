package com.financeapp.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AccountEntity {

    @Column(name = "checking")
    private String checking;

    @Column(name = "savings")
    private String savings;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "personal_loan")
    private String personalLoan;
}
