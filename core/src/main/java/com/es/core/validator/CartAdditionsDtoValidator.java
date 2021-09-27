package com.es.core.validator;

import com.es.core.dto.cart.CartAdditionDto;
import com.es.core.dto.cart.CartAdditionsDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
public class CartAdditionsDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CartAdditionsDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartAdditionsDto cartAdditionsDto = (CartAdditionsDto) target;
        Map<Integer, CartAdditionDto> additions = cartAdditionsDto.getAdditions();
        LinkedList<Integer> invalid = new LinkedList<>();
        // Check input
        additions.forEach((index, cartAdditionDto) -> {
            boolean isValid = true;
            if (cartAdditionDto.getModel().isBlank()) {
                errors.rejectValue("additions", "error.filed.blank");
                isValid = false;
            }
            if (cartAdditionDto.getQuantity() < 1) {
                errors.rejectValue("additions", "error.field.non-positive");
                isValid = false;
            }
            if (!isValid) {
                invalid.add(index);
            }
        });
        // Remove invalid input
        for (Integer index : invalid) {
            additions.remove(index);
        }
    }
}
