package com.sicario.hunters_league.service.dto;

import com.sicario.hunters_league.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private UUID id;

    @NotNull
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @ValidPassword
    @NotNull(message = "Password is required")
    private String password;

    @NotNull
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotNull
    @NotBlank(message = "CIN is required")
    private String cin;

    @NotNull
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotNull(message = "Join date is required")
    private LocalDateTime joinDate;

    @NotNull(message = "License expiration date is required")
    @Future(message = "License expiration date must be in the future")
    private LocalDateTime licenseExpirationDate;
}