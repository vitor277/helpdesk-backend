package com.vitor.helpdesck.services.exceptions;

public class DataIntegriyViolationException extends RuntimeException{
    private static final long SerialVersionUID = 1L;

    public DataIntegriyViolationException(String message) {
        super(message);
    }

    public DataIntegriyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
