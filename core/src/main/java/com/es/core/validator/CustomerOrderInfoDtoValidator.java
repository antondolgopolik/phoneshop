package com.es.core.validator;

import com.es.core.dto.order.CustomerOrderInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerOrderInfoDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerOrderInfoDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.field.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.field.empty");
    }
}
