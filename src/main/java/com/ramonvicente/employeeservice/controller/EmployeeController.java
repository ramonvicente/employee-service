package com.ramonvicente.employeeservice.controller;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeIdResult> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeIdResult result = employeeService.createEmployee(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
