package com.test.spring.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {
    private String endOfEmail;
    @Override
    public void initialize(CheckEmail constraintAnnotation) {
        endOfEmail = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext constraintValidatorContext) {

        return enteredValue.endsWith(endOfEmail);
    }
}
