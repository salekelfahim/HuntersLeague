package com.sicario.hunters_league.validation;

import com.sicario.hunters_league.annotation.UniqueUsername;
import com.sicario.hunters_league.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userRepository.existsByUsername(username);
    }
}
