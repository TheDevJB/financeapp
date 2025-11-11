package com.financeapp.finance.exception;

public class TransactionDoesNotExistException extends RuntimeException{

    public TransactionDoesNotExistException(){
        super("This transaction does not exist");
    }

    public TransactionDoesNotExistException(String message){
        super(message);
    }
}
