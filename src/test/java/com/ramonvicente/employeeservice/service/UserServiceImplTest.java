package com.ramonvicente.employeeservice.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ramonvicente.employeeservice.dto.auth.RegistrationRequest;
import com.ramonvicente.employeeservice.dto.auth.RegistrationResult;
import com.ramonvicente.employeeservice.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should register user given valid registration request.")
    public void shouldRegisterUserGivenValidRegistrationRequest() {
        //given
        RegistrationRequest request = RegistrationRequest.builder()
                .email("test@test.com")
                .username("username")
                .password("password")
                .build();
        Mockito.when(userRepository.findByUsername(request.getUsername())).thenReturn(null);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(null);

        //when
        RegistrationResult actual = userService.registerUser(request);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getMessage()).isEqualTo(String.format(UserServiceImpl.RESPONSE_MESSAGE_USER_REGISTERED_SUCCESSFULLY, request.getUsername()));
    }
}
