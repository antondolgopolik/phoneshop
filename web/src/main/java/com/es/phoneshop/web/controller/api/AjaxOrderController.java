package com.es.phoneshop.web.controller.api;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.dto.order.CustomerOrderInfoDto;
import com.es.core.exceptions.ValidationException;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.services.cart.CartService;
import com.es.core.services.order.OrderService;
import com.es.core.services.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
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

    @Resource
    private MessageSource messageSource;
    @Resource
    private Validator customerOrderInfoDtoValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(customerOrderInfoDtoValidator);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createOrder(@Validated @RequestBody CustomerOrderInfoDto customerOrderInfoDto,
                                           BindingResult bindingResult) {
        // Check errors
        if (bindingResult.hasErrors()) {
            throw new ValidationException(messageSource, bindingResult);
        }
        // Create order
        String username = userService.getUsername();
        Cart cart = cartService.getCart();
        Collection<CartItemDto> cartItems = cartService.getCartItems();
        Order order = orderService.createOrder(
                username, cart, deliveryPrice, cartItems,
                customerOrderInfoDto.getFirstName(), customerOrderInfoDto.getLastName(),
                customerOrderInfoDto.getAddress(), customerOrderInfoDto.getPhoneNumber(),
                customerOrderInfoDto.getAdditionalInfo()
        );
        // Send response
        return Collections.singletonMap("orderId", order.getId());
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
