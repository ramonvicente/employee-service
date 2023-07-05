package com.ramonvicente.employeeservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class ApiExceptionResponse {
    private int status;
    private String message;
    private ZonedDateTime timeStamp;
}
