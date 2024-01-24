package com.assignment.store.util.exception;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String errorMessage) {
        super(errorMessage);
    }
}
