package com.sicario.hunters_league.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class LoginVm {
    @NotNull
    @NotBlank(message = "Username is required")
    private String username;

    @NotNull
    @NotBlank(message = "Password is required")
    private String password;
}