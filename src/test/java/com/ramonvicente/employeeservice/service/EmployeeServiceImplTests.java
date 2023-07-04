package com.ramonvicente.employeeservice.service;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.model.Employee;
import com.ramonvicente.employeeservice.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Should return employee id when create employee given valid request")
    public void returnEmployeeIdWhenCreateEmployee() {
        //given
        EmployeeRequest request = EmployeeRequest.builder()
                .email("test@test.com")
                .firstName("name1")
                .lastName("name2")
                .birthday("1994-12-23")
                .hobbies(List.of("hobby1", "hobby2"))
                .build();

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(Employee.builder().id("employee-id").build());

        //when
        EmployeeIdResult result = employeeService.createEmployee(request);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("employee-id", result.getId());
    }

    @Test
    @DisplayName("Should throw exception when create employee given request null")
    public void throwExceptionWhenCreateEmployeeWithRequestNull() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            employeeService.createEmployee(null);
        });
    }
}
