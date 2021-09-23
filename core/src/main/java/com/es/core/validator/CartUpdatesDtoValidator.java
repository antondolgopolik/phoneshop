package com.es.core.validator;

import com.es.core.dto.cart.CartUpdatesDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;

@Component
public class CartUpdatesDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CartUpdatesDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CartUpdatesDto cartUpdatesDto = (CartUpdatesDto) target;
        ArrayList<Long> phoneIds = new ArrayList<>();
        // Check updates
        cartUpdatesDto.getUpdates().forEach((phoneId, quantity) -> {
            if (quantity < 1) {
                phoneIds.add(phoneId);
            }
        });
        // Check wrong updates
        if (!phoneIds.isEmpty()) {
            errors.rejectValue("updates", "error.cart.quantity-update", phoneIds.toArray(), null);
        }
    }
}
