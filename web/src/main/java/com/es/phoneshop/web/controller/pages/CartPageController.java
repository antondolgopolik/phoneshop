package com.es.phoneshop.web.controller.pages;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.model.cart.Cart;
import com.es.core.services.cart.CartService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";
    private static final String CART_ITEMS_ATTR = "cartItems";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCartPage(Model model) {
        Cart cart = cartService.getCart();
        boolean authenticated = userService.isAuthenticated();
        Collection<CartItemDto> cartItems = cartService.getCartItems();
        // Set attributes
        model.addAttribute(CART_ATTR, cart);
        model.addAttribute(AUTHENTICATED_ATR, authenticated);
        model.addAttribute(CART_ITEMS_ATTR, cartItems);
        return "cartPage";
    }
}
