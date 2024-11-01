package com.sicario.hunters_league.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        boolean hasMinLength = password.length() >= 8;

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);

        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);

        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        boolean isValid = hasMinLength && hasUppercase && hasLowercase && hasDigit;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "The password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one digit")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
