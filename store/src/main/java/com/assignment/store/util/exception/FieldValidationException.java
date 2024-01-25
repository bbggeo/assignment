package com.assignment.store.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class FieldValidationException extends RuntimeException {
    public FieldValidationException(String errorMessage) {
        super(errorMessage);
    }
}
