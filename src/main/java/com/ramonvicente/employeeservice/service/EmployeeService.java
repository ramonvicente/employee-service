package com.ramonvicente.employeeservice.service;

import java.util.List;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.dto.EmployeeResponse;
import com.ramonvicente.employeeservice.model.Employee;

public interface EmployeeService {

    /**
     * Creates a new {@link Employee} in the database from the provided {@link EmployeeRequest}.
     * 
     * @param employeeRequest DTO containing recipe data.
     * @return {@link EmployeeIdResult} DTO containing the saved {@link Employee} data.
     */
    public EmployeeIdResult createEmployee(EmployeeRequest employeeRequest);

    
    /**
     * Get list of all {@link Employee} from the database.
     * 
     * @return list of {@link EmployeeResponse} DTO containing the {@link Employee} data.
     */
    public List<EmployeeResponse> findEmployees();

    /**
     * Get{@link Employee} from the database provideing employee id.
     * 
     * @param employeeId {@link String} the employee id.
     * @return {@link EmployeeResponse} DTO containing the saved {@link Employee} data.
     */
    public EmployeeResponse findEmployeeByID(String employeeId);
}
