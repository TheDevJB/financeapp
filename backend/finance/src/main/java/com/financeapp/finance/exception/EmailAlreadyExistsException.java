package com.financeapp.finance.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("This email already exists");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
