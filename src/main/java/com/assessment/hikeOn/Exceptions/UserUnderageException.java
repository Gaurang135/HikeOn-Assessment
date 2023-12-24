package com.assessment.hikeOn.Exceptions;


public class UserUnderageException extends RuntimeException{
    public UserUnderageException() {
    }
    public UserUnderageException(String message) {
        super(message);
    }


}
