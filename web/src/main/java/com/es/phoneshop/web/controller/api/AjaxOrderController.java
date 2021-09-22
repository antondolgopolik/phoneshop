package com.es.phoneshop.web.controller.api;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.exceptions.MultiErrorException;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.services.cart.CartService;
import com.es.core.services.order.OrderService;
import com.es.core.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/orders")
public class AjaxOrderController {
    @Resource
    private UserService userService;
    @Resource
    private CartService cartService;
    @Resource
    private OrderService orderService;

    @Value("${order.deliveryPrice}")
    private BigDecimal deliveryPrice;

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultiErrorException.class)
    private ResponseEntity<Map<String, String>> handleMultiErrorException(MultiErrorException e) {
        //noinspection unchecked
        return new ResponseEntity<>((Map<String, String>) e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createOrder(@RequestParam(required = false) String firstName,
                                           @RequestParam(required = false) String lastName,
                                           @RequestParam(required = false) String address,
                                           @RequestParam(required = false) String phoneNumber,
                                           @RequestParam String additionalInfo) {
        // Check params
        checkParams(firstName, lastName, address, phoneNumber);
        // Create order
        String username = userService.getUsername();
        Cart cart = cartService.getCart();
        Collection<CartItemDto> cartItems = cartService.getCartItems();
        Order order = orderService.createOrder(
                username, cart, deliveryPrice, cartItems,
                firstName, lastName, address, phoneNumber, additionalInfo
        );
        // Send response
        return Collections.singletonMap("orderId", order.getId());
    }

    private void checkParams(String firstName, String lastName, String address, String phoneNumber) {
        Map<String, String> errors = new HashMap<>();
        checkParam("firstName", firstName, errors);
        checkParam("lastName", lastName, errors);
        checkParam("address", address, errors);
        checkParam("phoneNumber", phoneNumber, errors);
        // Check if errors were found
        if (!errors.isEmpty()) {
            throw new MultiErrorException(errors);
        }
    }

    private void checkParam(String paramName, String paramValue, Map<String, String> errors) {
        if ((paramValue == null) || paramValue.isBlank()) {
            errors.put(paramName, "Parameter '" + paramName + "' is required!");
        }
    }

    @RequestMapping(value = "/{orderId}/delivered", method = RequestMethod.PUT)
    public ResponseEntity<String> orderDelivered(@PathVariable String orderId) {
        // Update order status
        orderService.orderDelivered(orderId);
        // Send response
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{orderId}/rejected", method = RequestMethod.PUT)
    public ResponseEntity<String> orderRejected(@PathVariable String orderId) {
        // Update order status
        orderService.orderRejected(orderId);
        // Send response
        return ResponseEntity.noContent().build();
    }
}
