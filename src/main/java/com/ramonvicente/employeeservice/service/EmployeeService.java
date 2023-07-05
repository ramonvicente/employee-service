package com.ramonvicente.employeeservice.service;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.model.Employee;

public interface EmployeeService {

    /**
     * Creates a new {@link Employee} in the database from the provided {@link EmployeeRequest}.
     * @param employeeRequest DTO containing recipe data.
     * @return {@link EmployeeIdResult} DTO containing the saved {@link Employee} data.
     */
    public EmployeeIdResult createEmployee(EmployeeRequest employeeRequest);
}
