package com.ramonvicente.employeeservice.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailConflictException extends RuntimeException {

    public EmailConflictException(String message) {
        super(message);
    }
}
