package com.sicario.hunters_league.validation;

import com.sicario.hunters_league.annotation.UniqueEmail;
import com.sicario.hunters_league.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !userRepository.existsByEmail(email);
    }
}
