package com.ramonvicente.employeeservice.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank
    @Email(message = "Email format is not valid.")
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
