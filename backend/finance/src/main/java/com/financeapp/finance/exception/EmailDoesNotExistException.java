package com.financeapp.finance.exception;

public class EmailDoesNotExistException extends RuntimeException{

    public EmailDoesNotExistException(){
        super("This email was not found");
    }

    public EmailDoesNotExistException(String message){
        super(message);
    }
}
