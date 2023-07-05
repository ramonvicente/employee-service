package com.ramonvicente.employeeservice.repository;

import com.ramonvicente.employeeservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    /**
     * Get a {@link Employee} from the database by email.
     * @param email is the email of the {@link Employee}.
     * @return {@link Employee} from the database.
     */
    @Query("{ 'name' : ?0 }")
    public Employee findByEmail(String email);
}
