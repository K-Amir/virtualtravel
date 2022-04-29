package com.virtualtravel.empresa.ErrorHandling;

public class RequiredQueryParamException extends RuntimeException {
    public RequiredQueryParamException(String msg) {
        super(msg);
    }
}
