package com.financeapp.finance.exceptions;

public class EmailDoesNotExistException extends RuntimeException{

    public EmailDoesNotExistException(String message){
        super("This email was not found");
    }

}
