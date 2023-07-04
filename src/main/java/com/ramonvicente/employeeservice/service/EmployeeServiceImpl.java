package com.ramonvicente.employeeservice.service;

import com.ramonvicente.employeeservice.converter.EmployeeConverter;
import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.model.Employee;
import com.ramonvicente.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeIdResult createEmployee(EmployeeRequest employeeRequest) {
        if(employeeRequest == null) {
            throw new IllegalArgumentException();
        }

        Employee employeeToSave = EmployeeConverter.toEmployee(employeeRequest);

        Employee newEmployee = employeeRepository.save(employeeToSave);
        log.info("Saving employee: {}", newEmployee.getId());

        return EmployeeConverter.toEmployeeIdResult(newEmployee);
    }
}
