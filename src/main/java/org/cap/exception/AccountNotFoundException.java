package org.cap.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String msg){
        super(msg);
    }

    public AccountNotFoundException(String msg,Throwable e){
        super(msg,e);
    }
}
