package com.assessment.hikeOn.Exceptions;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException() {
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}
