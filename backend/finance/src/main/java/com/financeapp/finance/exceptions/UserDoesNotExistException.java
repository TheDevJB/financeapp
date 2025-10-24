package com.financeapp.finance.exceptions;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(){
        super("The user you are looking for does not exist");
    }

    public UserDoesNotExistException(String message){
        super(message);
    }
}
