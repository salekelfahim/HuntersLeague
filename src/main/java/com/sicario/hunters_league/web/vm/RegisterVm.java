package com.sicario.hunters_league.web.vm;

import com.sicario.hunters_league.annotation.UniqueEmail;
import com.sicario.hunters_league.annotation.UniqueUsername;
import com.sicario.hunters_league.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterVm {
    @UniqueUsername
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "CIN is required")
    private String cin;

    @UniqueEmail
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private String nationality;

    private LocalDateTime joinDate = LocalDateTime.now();

    @Future(message = "License expiration date must be in the future")
    private LocalDateTime licenseExpirationDate;
}
