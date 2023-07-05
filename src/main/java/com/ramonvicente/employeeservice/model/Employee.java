package com.ramonvicente.employeeservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(value = "employees")
@Data
@Builder
public class Employee {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String email;
    private String fullName;
    private String birthday;
    private List<String> hobbies;
}
