package com.ramonvicente.employeeservice.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ramonvicente.employeeservice.dto.auth.LoginRequest;
import com.ramonvicente.employeeservice.dto.auth.RegistrationRequest;
import com.ramonvicente.employeeservice.dto.auth.RegistrationResult;
import com.ramonvicente.employeeservice.dto.auth.TokenResponse;
import com.ramonvicente.employeeservice.service.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RegistrationResult register(@Valid @RequestBody RegistrationRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
