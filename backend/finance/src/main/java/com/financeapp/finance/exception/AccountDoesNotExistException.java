package com.financeapp.finance.exception;

public class AccountDoesNotExistException extends RuntimeException{

    public AccountDoesNotExistException(){
        super("This Account does not exist");
    }

    public AccountDoesNotExistException(String message){
        super(message);
    }
}
