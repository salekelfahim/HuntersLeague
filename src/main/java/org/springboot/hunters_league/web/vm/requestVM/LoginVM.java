package org.springboot.hunters_league.web.vm.requestVM;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVM {
    @NotBlank(message = "Username cannot be blank.")
    private String username;

    @Size(min = 8, message = "The password must be at least 8 characters long.")
    @Pattern(regexp = ".*[A-Z].*", message = "The password must contain at least one uppercase letter.")
    @Pattern(regexp = ".*[a-z].*", message = "The password must contain at least one lowercase letter.")
    @Pattern(regexp = ".*\\d.*", message = "The password must contain at least one digit.")
    private String password;
}
