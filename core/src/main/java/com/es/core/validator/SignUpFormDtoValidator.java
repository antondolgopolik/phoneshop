package com.es.core.validator;

import com.es.core.dto.user.SignUpFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.field.empty");
    }
}
