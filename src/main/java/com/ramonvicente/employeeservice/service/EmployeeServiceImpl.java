package com.ramonvicente.employeeservice.service;

import java.util.List;

import com.ramonvicente.employeeservice.converter.EmployeeConverter;
import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.dto.EmployeeResponse;
import com.ramonvicente.employeeservice.exception.http.EmailConflictException;
import com.ramonvicente.employeeservice.model.Employee;
import com.ramonvicente.employeeservice.repository.EmployeeRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeIdResult createEmployee(@Valid EmployeeRequest employeeRequest) {
        if(employeeRequest == null) {
            throw new IllegalArgumentException();
        }
        validateEmail(employeeRequest.getEmail());

        Employee employeeToSave = EmployeeConverter.toEmployee(employeeRequest);

        Employee newEmployee = employeeRepository.save(employeeToSave);
        log.info("Saving employee: {}", newEmployee.getId());

        return EmployeeConverter.toEmployeeIdResult(newEmployee);
    }

    @Override
    public List<EmployeeResponse> findEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                        .map(e -> EmployeeConverter.toEmployeeResponse(e))
                        .toList();
    }

    private void validateEmail(String email) {
        if(employeeRepository.findByEmail(email) != null) {
            throw new EmailConflictException(String.format("Employee with email '%s' already exist.", email));
        }
    }
}
