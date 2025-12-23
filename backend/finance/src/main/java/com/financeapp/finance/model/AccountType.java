package com.financeapp.finance.model;

public enum AccountType {
    CHECKING,
    SAVINGS,
    CREDIT_CARD,
    AFFIRM,
    AFTER_PAY,
    KLARNA,
    PERSONAL_LOAN,
    SCHOOL_LOAN,
    INVESTMENT,
    CAR_LOAN,
    MORTGAGE,
    RENT;

    public boolean isDebtAccount() {
        return this == CREDIT_CARD || this == PERSONAL_LOAN || this == SCHOOL_LOAN || this == CAR_LOAN
                || this == AFTER_PAY
                || this == AFFIRM || this == KLARNA || this == MORTGAGE || this == AFFIRM;
    }
}