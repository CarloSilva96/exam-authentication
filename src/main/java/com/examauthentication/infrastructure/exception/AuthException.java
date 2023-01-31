package com.examauthentication.infrastructure.exception;

public class AuthException extends RuntimeException{
    public AuthException(){ }

    public AuthException(String msgError){
        super(msgError);
    }

    public AuthException(String msgError, Throwable throwable){
        super(msgError, throwable);
    }
}
