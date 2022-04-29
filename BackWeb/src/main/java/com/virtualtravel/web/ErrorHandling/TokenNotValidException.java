package com.virtualtravel.web.ErrorHandling;

public class TokenNotValidException extends RuntimeException{
    public TokenNotValidException(String msg){
        super(msg);
    }
}
