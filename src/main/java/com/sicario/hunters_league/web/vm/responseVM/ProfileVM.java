package com.sicario.hunters_league.web.vm.responseVM;

import com.sicario.hunters_league.domain.Enum.Role;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileVM {

    private String username;

    private Role role;


    private String firstName;


    private String lastName;

    private String cin;


    private String email;

    private String nationality;

    private LocalDateTime joinDate;


    private LocalDateTime licenseExpirationDate;

}
