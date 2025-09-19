package com.financeapp.finance.exceptions;

public class PhoneTakenException extends RuntimeException{

    public PhoneTakenException(String message){
        super("This Phone Number does not exist");
    }

}
