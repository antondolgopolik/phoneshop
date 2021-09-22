package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.services.cart.CartService;
import com.es.core.services.order.OrderService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/orderList")
public class AdminOrderListPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";
    private static final String ORDERS_ATTR = "orders";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String showOrderListPage(Model model) {
        Cart cart = cartService.getCart();
        boolean authenticated = userService.isAuthenticated();
        List<Order> orders = orderService.getOrders();
        // Set attributes
        model.addAttribute(CART_ATTR, cart);
        model.addAttribute(AUTHENTICATED_ATR, authenticated);
        model.addAttribute(ORDERS_ATTR, orders);
        return "admin/adminOrderListPage";
    }
}
