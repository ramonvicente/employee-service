package com.ramonvicente.employeeservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ramonvicente.employeeservice.model.User;


public interface UserRepository  extends  MongoRepository<User, String> {

    /**
     * Get a {@link User} from the database by username.
     * @param username is the email of the {@link User}.
     * @return {@link User} from the database.
     */
    @Query("{ 'username' : ?0 }")
    User findByUsername(String username);

}
