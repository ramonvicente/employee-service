package com.ramonvicente.employeeservice.config;

import com.ramonvicente.employeeservice.repository.EmployeeRepository;
import com.ramonvicente.employeeservice.service.EmployeeService;
import com.ramonvicente.employeeservice.service.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository) {
        return new EmployeeServiceImpl(employeeRepository);
    }
}
