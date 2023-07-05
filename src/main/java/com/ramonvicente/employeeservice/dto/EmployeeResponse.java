package com.ramonvicente.employeeservice.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private String id;
    private String email;
    private String fullName;
    private String birthday;
    private List<String> hobbies;
}
