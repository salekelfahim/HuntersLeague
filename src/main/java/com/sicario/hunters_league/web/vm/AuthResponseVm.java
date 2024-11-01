package com.sicario.hunters_league.web.vm;

import com.sicario.hunters_league.domain.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseVm {
    private UUID id;
    private String username;
    private String email;
}
