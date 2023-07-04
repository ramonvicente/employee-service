package com.ramonvicente.employeeservice.exception;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentExceptionResponse> handleValidationErrors(MethodArgumentNotValidException exception) {
        List<Violation> violations = getViolations(exception);
        ArgumentExceptionResponse argumentExceptionResponse = ArgumentExceptionResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error(HttpStatus.BAD_REQUEST)
            .violations(violations)
            .timeStamp(ZonedDateTime.now())
            .build();
        return new ResponseEntity<>(argumentExceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ApiExceptionResponse> handleRuntimeExceptions(RuntimeException exception) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(exception.getMessage())
            .timeStamp(ZonedDateTime.now())
            .build();
        return new ResponseEntity<>(apiExceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<Violation> getViolations(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
            .getFieldErrors().stream()
            .map(e -> new Violation(e.getField(), e.getDefaultMessage()))
            .toList();
    }
    
}
