package com.assignment.store.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class FieldValidationException extends ErrorResponseException {

    public FieldValidationException(String errorMessage) {
        super(HttpStatus.EXPECTATION_FAILED, ProblemDetail.forStatusAndDetail(HttpStatus.EXPECTATION_FAILED, errorMessage), null);
    }
}
