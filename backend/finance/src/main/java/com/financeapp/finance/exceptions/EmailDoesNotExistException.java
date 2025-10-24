package com.financeapp.finance.exceptions;

public class EmailDoesNotExistException extends RuntimeException{

    public EmailDoesNotExistException(){
        super("This email was not found");
    }

    public EmailDoesNotExistException(String message){
        super(message);
    }
}
