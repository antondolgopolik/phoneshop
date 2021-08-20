package com.es.phoneshop.web.controller.pages;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.model.cart.Cart;
import com.es.core.services.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCart(Model model) {
        Cart cart = cartService.getCart();
        Collection<CartItemDto> cartItems = cartService.getCartItems();
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", cartItems);
        return "cartPage";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCart() {

    }
}
