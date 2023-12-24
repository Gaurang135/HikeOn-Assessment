package com.assessment.hikeOn.Exceptions;

public class RegisteredMailException extends RuntimeException{
    public RegisteredMailException() {
    }
    public RegisteredMailException(String message) {
        super(message);
    }
}
