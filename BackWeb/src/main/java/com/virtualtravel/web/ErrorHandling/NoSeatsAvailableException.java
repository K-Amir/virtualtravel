package com.virtualtravel.web.ErrorHandling;

public class NoSeatsAvailableException extends RuntimeException{
    public NoSeatsAvailableException(String message){
        super(message);
    }
}
