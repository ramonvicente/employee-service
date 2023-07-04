package com.ramonvicente.employeeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@SpringBootTest(properties = {"spring.data.mongodb.database=employees-test"})
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerIT {

    @Autowired
    private MockMvc mockMvc;

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

    private String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
