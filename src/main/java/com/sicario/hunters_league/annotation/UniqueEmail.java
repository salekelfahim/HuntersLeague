package com.sicario.hunters_league.annotation;

import com.sicario.hunters_league.validation.UniqueEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email already exists";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
