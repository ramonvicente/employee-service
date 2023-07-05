package com.ramonvicente.employeeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ramonvicente.employeeservice.repository.UserRepository;
import com.ramonvicente.employeeservice.service.UserService;
import com.ramonvicente.employeeservice.service.UserServiceImpl;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(UserRepository repository,
                                   AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(repository, authenticationManager, passwordEncoder);
    }
}
