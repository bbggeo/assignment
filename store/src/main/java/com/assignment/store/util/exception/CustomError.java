package com.assignment.store.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class CustomError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public CustomError() {
        timestamp = LocalDateTime.now();
    }

    public CustomError(HttpStatus status, String message) {
        this();
        this.status = status;
    }

}
