package com.ramonvicente.employeeservice.repository;

import com.ramonvicente.employeeservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<String, Employee> {
}
