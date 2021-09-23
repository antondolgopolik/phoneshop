package com.es.phoneshop.web.controller.pages;

import com.es.core.dto.order.OrderItemDto;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.services.cart.CartService;
import com.es.core.services.order.OrderService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";
    private static final String ORDER_ATTR = "order";
    private static final String ORDER_ITEMS_ATTR = "orderItems";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public String showOrderOverviewPage(Model model,
                                        @PathVariable String orderId) {
        Cart cart = cartService.getCart();
        boolean authenticated = userService.isAuthenticated();
        Order order = orderService.getOrder(orderId);
        Collection<OrderItemDto> orderItems = orderService.getOrderItems(orderId);
        // Set attributes
        model.addAttribute(CART_ATTR, cart);
        model.addAttribute(AUTHENTICATED_ATR, authenticated);
        model.addAttribute(ORDER_ATTR, order);
        model.addAttribute(ORDER_ITEMS_ATTR, orderItems);
        return "orderOverviewPage";
    }
}
