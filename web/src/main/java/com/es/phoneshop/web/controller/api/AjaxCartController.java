package com.es.phoneshop.web.controller.api;

import com.es.core.exceptions.MultiErrorException;
import com.es.core.model.cart.Cart;
import com.es.core.services.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/cart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultiErrorException.class)
    private ResponseEntity<Map<Long, String>> handleMultiErrorException(MultiErrorException e) {
        //noinspection unchecked
        return new ResponseEntity<>((Map<Long, String>) e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cartItems/{phoneId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addToCart(@PathVariable Long phoneId,
                                         @RequestParam Integer quantity) {
        // Check parameters
        checkPhoneId(phoneId);
        checkQuantity(quantity);
        // Add to cart
        cartService.addToCart(phoneId, quantity);
        // Response
        return responseWithCartInfo();
    }

    @RequestMapping(value = "/cartItems", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateCart(@RequestBody Map<Long, Integer> updates) {
        // Check updates
        checkUpdates(updates);
        // Update
        cartService.updateCart(updates);
        // Response
        return responseWithCartInfo();
    }

    @RequestMapping(value = "/cartItems/{phoneId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteFromCart(@PathVariable Long phoneId) {
        // Check parameters
        checkPhoneId(phoneId);
        // Remove from cart
        cartService.deleteFromCart(phoneId);
        // Response
        return responseWithCartInfo();
    }

    private void checkPhoneId(Long phoneId) {
        if (phoneId < 1) {
            throw new IllegalArgumentException("Phone id should be positive integer!");
        }
    }

    private void checkQuantity(Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity should be positive integer!");
        }
    }

    private void checkUpdates(Map<Long, Integer> updates) {
        Map<Long, String> errors = new HashMap<>();
        updates.forEach((phoneId, quantity) -> {
            if (quantity < 1) {
                errors.put(phoneId, "Quantity should be positive integer!");
            }
        });
        if (!errors.isEmpty()) {
            throw new MultiErrorException(errors);
        }
    }

    private Map<String, Object> responseWithCartInfo() {
        Cart cart = cartService.getCart();
        Map<String, Object> response = new HashMap<>();
        response.put("totalQuantity", cart.getTotalQuantity());
        response.put("totalCost", cart.getSubtotal());
        return response;
    }
}
