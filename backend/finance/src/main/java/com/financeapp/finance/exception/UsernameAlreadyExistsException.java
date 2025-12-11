package com.financeapp.finance.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("This username already exists");
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
