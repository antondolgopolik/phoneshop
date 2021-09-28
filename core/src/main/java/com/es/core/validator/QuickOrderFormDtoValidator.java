package com.es.core.validator;

import com.es.core.dto.cart.CartAdditionDto;
import com.es.core.dto.cart.QuickOrderFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;

@Component
public class QuickOrderFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return QuickOrderFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuickOrderFormDto quickOrderFormDto = (QuickOrderFormDto) target;
        ArrayList<CartAdditionDto> cartAdditions = quickOrderFormDto.getCartAdditions();
        // Check if cart additions are present
        if (cartAdditions == null) {
            return;
        }
        // Validate cart additions
        for (int i = 0; i < cartAdditions.size(); i++) {
            CartAdditionDto cartAddition = cartAdditions.get(i);
            String model = cartAddition.getModel();
            Integer quantity = cartAddition.getQuantity();
            if ((model != null) && !model.isBlank() && (quantity == null)) {
                errors.rejectValue(
                        "cartAdditions[" + i + "].quantity",
                        "validation.field.empty",
                        "Field 'Quantity' is required"
                );
            } else if (((model == null) || model.isBlank()) && (quantity != null)) {
                errors.rejectValue(
                        "cartAdditions[" + i + "].model",
                        "validation.field.empty",
                        "Field 'Model' is required"
                );
            }
        }
    }
}
