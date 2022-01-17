package com.gamedirectory.model.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GamerValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestValidator {
    String message() default "Bad Request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
