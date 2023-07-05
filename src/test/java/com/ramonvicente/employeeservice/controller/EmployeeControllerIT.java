package com.ramonvicente.employeeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.model.Employee;
import com.ramonvicente.employeeservice.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(properties = {"spring.data.mongodb.database=employees-test"})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeControllerIT {

    private static final String EMPLOYEE_ID_1 = "e3c32f32-0d92-4789-939a-26aa5747b9b3";
    private static final String EMPLOYEE_ID_2 = "e86d0d4c-1b1d-11ee-be56-0242ac120002";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeAll
    public void setup() {
        employeeRepository.deleteAll(); //remove all data from db test
        addEmployeeToDB();
    }

    @Test
    @DisplayName("Return status created when create new employee.")
    public void returnStatusCreatedWhenCreateNewEmployee() throws Exception {

        EmployeeRequest request = EmployeeRequest.builder()
            .email("test@test.com")
            .firstName("first")
            .lastName("last33")
            .birthday("1996-03-12")
            .hobbies(List.of("hobby1"))
            .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(request)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    @Test
    @DisplayName("Should return status bad request when request has invalid field.")
    public void returnStatusBadRequestWhenEmployeeRequestHasInvalidField() throws Exception {

        EmployeeRequest request = EmployeeRequest.builder()
            .email("wrong-email")
            .firstName("first")
            .lastName("last33")
            .birthday("1996-03-12")
            .hobbies(List.of("hobby1"))
            .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(request)))
            .andExpect(MockMvcResultMatchers
                .status()
                .isBadRequest());
    }

    @Test
    @DisplayName("Return status ok when find employees.")
    public void returnStatusOkWhenFindEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    @DisplayName("Return status ok when find employee by id.")
    public void returnStatusOkWhenFindEmployeeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/employees/" + EMPLOYEE_ID_1))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    private String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addEmployeeToDB() {
        Employee employee = Employee.builder()
                .id(EMPLOYEE_ID_1)
                .email("employee@test.com")
                .fullName("full name")
                .birthday("1994-12-23")
                .hobbies(List.of("hobby1", "hobby2"))
                .build();
        
                Employee employee2 = Employee.builder()
                .id(EMPLOYEE_ID_2)
                .email("employee2@test.com")
                .fullName("full name")
                .birthday("1994-02-23")
                .hobbies(List.of("hobby1", "hobby2"))
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);

        employeeRepository.saveAll(employees);
    }
}
