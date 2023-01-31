package com.examauthentication.infrastructure.exception;

public class DataIntegrityException extends RuntimeException{
    public DataIntegrityException(String msgErro){
        super(msgErro);
    }
}
