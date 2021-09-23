package com.es.phoneshop.web.controller.api;

import com.es.core.dto.cart.CartUpdatesDto;
import com.es.core.dto.cart.QuantityDto;
import com.es.core.exceptions.ValidationException;
import com.es.core.model.cart.Cart;
import com.es.core.services.cart.CartService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/cart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @Resource
    private MessageSource messageSource;
    @Resource
    private Validator quantityDtoValidator;
    @Resource
    private Validator cartUpdatesDtoValidator;

    @InitBinder("quantityDto")
    private void initBinderQuantityDto(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(quantityDtoValidator);
    }

    @InitBinder("cartUpdatesDto")
    private void initBinderCartUpdatesDto(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(cartUpdatesDtoValidator);
    }

    @RequestMapping(value = "/cartItems/{phoneId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addToCart(@PathVariable Long phoneId,
                                         @Validated @RequestBody QuantityDto quantityDto,
                                         BindingResult bindingResult) {
        // Check errors
        if (bindingResult.hasErrors()) {
            throw new ValidationException(messageSource, bindingResult);
        }
        // Add to cart
        cartService.addToCart(phoneId, quantityDto.getQuantity());
        // Response
        return responseWithCartInfo();
    }

    @RequestMapping(value = "/cartItems", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateCart(@Validated @RequestBody CartUpdatesDto cartUpdatesDto,
                                          BindingResult bindingResult) {
        // Check errors
        if (bindingResult.hasErrors()) {
            throwUpdatesValidationException(bindingResult);
        }
        // Update
        cartService.updateCart(cartUpdatesDto.getUpdates());
        // Response
        return responseWithCartInfo();
    }

    @RequestMapping(value = "/cartItems/{phoneId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteFromCart(@PathVariable Long phoneId) {
        // Remove from cart
        cartService.deleteFromCart(phoneId);
        // Response
        return responseWithCartInfo();
    }

    @SuppressWarnings("ConstantConditions")
    private void throwUpdatesValidationException(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError("updates");
        String message = messageSource.getMessage(fieldError.getCode(), null, Locale.ENGLISH);
        Object[] phoneIds = fieldError.getArguments();
        // Create error messages
        Map<Long, String> messages = new HashMap<>();
        for (Object phoneId : phoneIds) {
            messages.put((Long) phoneId, message);
        }
        // Throw exception
        throw new ValidationException(messages);
    }

    private Map<String, Object> responseWithCartInfo() {
        Cart cart = cartService.getCart();
        Map<String, Object> response = new HashMap<>();
        response.put("totalQuantity", cart.getTotalQuantity());
        response.put("totalCost", cart.getSubtotal());
        return response;
    }
}
