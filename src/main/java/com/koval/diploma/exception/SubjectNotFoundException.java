package com.koval.diploma.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
