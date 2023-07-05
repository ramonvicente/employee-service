package com.ramonvicente.employeeservice.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
public class ApiExceptionResponse {
    private int status;
    private HttpStatus error;
    private String message;
    private ZonedDateTime timeStamp;
}
