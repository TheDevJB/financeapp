package com.financeapp.finance.exceptions;

public class EmailTakenException extends RuntimeException{

    public EmailTakenException(String message){
        super("This email does not exist");
    }
}
