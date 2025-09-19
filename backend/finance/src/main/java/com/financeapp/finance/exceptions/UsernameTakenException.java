package com.financeapp.finance.exceptions;

public class UsernameTakenException extends RuntimeException{

    public UsernameTakenException(String message){
        super("This Username does not exist");
    }
}
