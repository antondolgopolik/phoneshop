package com.es.core.validator;

import com.es.core.dto.cart.QuantityDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuantityDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return QuantityDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuantityDto quantityDto = (QuantityDto) target;
        if (quantityDto.getQuantity() < 1) {
            errors.rejectValue("quantity", "error.field.non-positive");
        }
    }
}
