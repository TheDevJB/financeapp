package com.financeapp.finance.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(){
        super("The user you are looking for does not exist");
    }

    public UserDoesNotExistException(String message){
        super(message);
    }
}
