package com.es.phoneshop.web.controller.pages;

import com.es.core.services.cart.CartService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/quickOrder")
public class QuickOrderPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showQuickOrderPage(Model model) {
        // Set attributes
        model.addAttribute(CART_ATTR, cartService.getCart());
        model.addAttribute(AUTHENTICATED_ATR, userService.isAuthenticated());
        return "quickOrderPage";
    }
}
