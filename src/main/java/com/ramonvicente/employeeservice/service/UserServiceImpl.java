package com.ramonvicente.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramonvicente.employeeservice.config.security.JwtUtils;
import com.ramonvicente.employeeservice.dto.auth.LoginRequest;
import com.ramonvicente.employeeservice.dto.auth.RegistrationRequest;
import com.ramonvicente.employeeservice.dto.auth.RegistrationResult;
import com.ramonvicente.employeeservice.dto.auth.TokenResponse;
import com.ramonvicente.employeeservice.model.User;
import com.ramonvicente.employeeservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String RESPONSE_MESSAGE_USER_ALREADY_EXIST = "User %s already exist.";
    public static final String RESPONSE_MESSAGE_EMAIL_ALREADY_EXIST = "User with email %s was already exist.";
    public static final String ERROR_MESSAGE_REGISTRATION_NULL = "registration should not be null.";
    public static final String RESPONSE_MESSAGE_USER_REGISTERED_SUCCESSFULLY = "User %s was registered successfully.";

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public RegistrationResult registerUser(RegistrationRequest registration) {
        if(registration == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_REGISTRATION_NULL);
        }
        String message;
        if (userRepository.findByUsername(registration.getUsername()) != null) {
            message = String.format(RESPONSE_MESSAGE_USER_ALREADY_EXIST, registration.getUsername());
            return RegistrationResult.builder()
                    .message(message)
                    .build();
        }
        if (userRepository.findByEmail(registration.getEmail()) != null) {
            message = String.format(RESPONSE_MESSAGE_EMAIL_ALREADY_EXIST, registration.getUsername());
            return RegistrationResult.builder()
                    .message(message)
                    .build();
        }
        User user = User.builder()
                .email(registration.getEmail())
                .username(registration.getUsername())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        userRepository.save(user);
        message = String.format(RESPONSE_MESSAGE_USER_REGISTERED_SUCCESSFULLY, registration.getUsername());
        return RegistrationResult.builder()
                .message(message)
                .build();
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return TokenResponse.builder()
                .accessToken(jwt)
                .build();
    }
}
