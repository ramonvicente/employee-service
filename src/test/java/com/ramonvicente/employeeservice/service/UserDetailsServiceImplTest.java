package com.ramonvicente.employeeservice.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import com.ramonvicente.employeeservice.model.User;
import com.ramonvicente.employeeservice.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @DisplayName("Should return userDetail when LoadUerByUsername given valid username.")
    public void shouldReturnUserDetailsWhenLoadUserByUsernameGivenValidUsername() {
        //given
        String username = "username";
        User user = User.builder().email("email@email.com").username("username").build();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);

        //when
        UserDetails actual = userDetailsService.loadUserByUsername(username);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(username);
    }
}
