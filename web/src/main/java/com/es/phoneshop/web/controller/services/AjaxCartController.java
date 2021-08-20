package com.es.phoneshop.web.controller.services;

import com.es.core.dao.cart.CartDao;
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
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartDao cartDao;
    @Resource
    private CartService cartService;

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> addPhone(@RequestParam("phoneId") Long phoneId,
                                        @RequestParam("quantity") Integer quantity) {
        // Check parameters
        checkPhoneId(phoneId);
        checkQuantity(quantity);
        // Add to cart
        cartService.add(phoneId, quantity);
        // Response
        Cart cart = cartDao.get();
        Map<String, Object> response = new HashMap<>();
        response.put("totalQuantity", cart.getTotalQuantity());
        response.put("totalCost", cart.getTotalCost());
        return response;
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
}
