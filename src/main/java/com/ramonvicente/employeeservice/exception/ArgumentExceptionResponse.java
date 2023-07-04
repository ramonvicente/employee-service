package com.ramonvicente.employeeservice.exception;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Builder;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ArgumentExceptionResponse {
    private int status;
    private HttpStatus error;
    private List<Violation> violations;
    private ZonedDateTime timeStamp;
}
