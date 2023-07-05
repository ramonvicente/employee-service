package com.ramonvicente.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramonvicente.employeeservice.dto.auth.UserDetailsImpl;
import com.ramonvicente.employeeservice.exception.http.NotFoundException;
import com.ramonvicente.employeeservice.model.User;
import com.ramonvicente.employeeservice.repository.UserRepository;
import com.ramonvicente.employeeservice.utils.Utility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK = "username should not be null or blank.";

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utility.checkArgument(username == null || username.isBlank(), ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK);
        
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new NotFoundException("User Not Found with username: " + username);
        }

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
