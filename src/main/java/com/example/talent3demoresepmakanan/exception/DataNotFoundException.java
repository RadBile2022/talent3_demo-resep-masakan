package com.example.talent3demoresepmakanan.exception;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 8082319414278002853L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
