package com.ramonvicente.employeeservice.repository;

import com.ramonvicente.employeeservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
