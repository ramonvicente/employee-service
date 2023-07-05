package com.ramonvicente.employeeservice.service;

import com.ramonvicente.employeeservice.dto.EmployeeIdResult;
import com.ramonvicente.employeeservice.dto.EmployeeRequest;
import com.ramonvicente.employeeservice.dto.EmployeeResponse;
import com.ramonvicente.employeeservice.exception.http.ConflictException;
import com.ramonvicente.employeeservice.exception.http.NotFoundException;
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
import java.util.Optional;

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

        Mockito.when(employeeRepository.findByEmail(request.getEmail())).thenReturn(new Employee());

        //then
        Assert.assertThrows(ConflictException.class, () -> {
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

    @Test
    @DisplayName("Should return employee when find employee by id given valid argument.")
    public void returnEmployeeWhenFindEmployeeById() {
        //given
        String employeeId = "employee-id";
        Employee employee = Employee.builder()
                .id(employeeId)
                .email("test@test.com")
                .fullName("full name")
                .birthday("1994-12-23")
                .hobbies(List.of("hobby1", "hobby2"))
                .build();

        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        //when
        EmployeeResponse result = employeeService.findEmployeeByID(employeeId);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(employeeId, result.getId());
    }

    @Test
    @DisplayName("Should throw exception when find employee by id given empty argument.")
    public void throwExceptionWhenFindEmployeeByIdWithGivenEmptyArgument() {

        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findEmployeeByID("");
        });

        Assertions.assertEquals(EmployeeServiceImpl.ERROR_MESSAGE_EMPLOYEE_ID_MUST_HAVE_VALUE, exception.getMessage());

    }

    @Test
    @DisplayName("Should throw exception when find employee by id given null argument.")
    public void throwExceptionWhenFindEmployeeByIdWithGivenNullArgument() {

        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            employeeService.findEmployeeByID(null);
        });

        Assertions.assertEquals(EmployeeServiceImpl.ERROR_MESSAGE_EMPLOYEE_ID_MUST_HAVE_VALUE, exception.getMessage());

    }

    @Test
    @DisplayName("Should throw exception when find employee by id given not existing employee id.")
    public void throwExceptionWhenFindEmployeeByIdWithGivenNotExistingEmployeeId() {
        //given
        String notExistingEmployeeId = "employee-id";

        //when
        NotFoundException exception = Assert.assertThrows(NotFoundException.class, () -> {
            employeeService.findEmployeeByID(notExistingEmployeeId);
        });

        //then
        String expectedMessage = 
            String.format(EmployeeServiceImpl.ERROR_MESSAGE_EMPLOYEE_NOT_FOUND, notExistingEmployeeId);

        Assertions.assertEquals(expectedMessage, exception.getMessage());

    }
}
