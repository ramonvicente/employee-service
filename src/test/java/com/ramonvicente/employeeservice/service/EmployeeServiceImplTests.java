package com.ramonvicente.employeeservice.service;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.dto.EmployeeResponse;
import com.ramonvicente.employeeservice.exception.http.EmailConflictException;
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

    @Test
    @DisplayName("Should throw exception when create employee given request with existing email.")
    public void throwExceptionWhenCreateEmployeeWithExistingEmail() {
        //given
        EmployeeRequest request = EmployeeRequest.builder()
            .email("test@test.com")
            .build();

        Mockito.when(employeeRepository.findByEmail(request.getEmail())).thenReturn(Mockito.any(Employee.class));

        //then
        Assert.assertThrows(EmailConflictException.class, () -> {
            employeeService.createEmployee(request);
        });
    }

    @Test
    @DisplayName("Should return employee list when find employees")
    public void returnAllEmployeesWhenFindEmployees() {
        //given
        Employee employee = Employee.builder()
                .email("test@test.com")
                .fullName("full name")
                .birthday("1994-12-23")
                .hobbies(List.of("hobby1", "hobby2"))
                .build();

        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(employee));

        //when
        List<EmployeeResponse> result = employeeService.findEmployees();

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() == 1);
        Assertions.assertEquals("test@test.com", result.get(0).getEmail());
    }

    @Test
    @DisplayName("Should return empty list when find employees has no employees")
    public void returnEmptyWhenFindEmployees() {
        //given
        Mockito.when(employeeRepository.findAll()).thenReturn(List.of());

        //when
        List<EmployeeResponse> result = employeeService.findEmployees();

        //then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() == 0);
    }
}
