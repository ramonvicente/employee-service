package com.ramonvicente.employeeservice.converter;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.model.Employee;

public class EmployeeConverter {

    public static Employee toEmployee(EmployeeRequest request) {
        return Employee.builder()
            .email(request.getEmail())
            .fullName(String.format("%s %s", request.getFirstName(), request.getLastName()))
            .birthday(request.getBirthday())
            .hobbies(request.getHobbies())
            .build();
    }

    public static EmployeeIdResult toEmployeeIdResult(Employee employee) {
        return  EmployeeIdResult.builder()
            .id(employee.getId())
            .build();
    }
}
