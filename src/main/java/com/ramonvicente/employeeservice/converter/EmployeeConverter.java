package com.ramonvicente.employeeservice.converter;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.dto.EmployeeResponse;
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

    public static Employee toEmployee(EmployeeRequest request, String employeeId) {
        return Employee.builder()
            .id(employeeId)
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

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                                .id(employee.getId())
                                .email(employee.getEmail())
                                .fullName(employee.getFullName())
                                .birthday(employee.getBirthday())
                                .hobbies(employee.getHobbies())
                                .build();
    }
}
