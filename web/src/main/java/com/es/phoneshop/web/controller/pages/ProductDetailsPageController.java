package com.es.phoneshop.web.controller.pages;

import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import com.es.core.services.cart.CartService;
import com.es.core.services.product.ProductService;
import com.es.core.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    private static final String CART_ATTR = "cart";
    private static final String AUTHENTICATED_ATR = "authenticated";
    private static final String PRODUCT_ATTR = "product";

    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.GET)
    public String showProductDetailsPage(Model model,
                                         @PathVariable Long phoneId) {
        Cart cart = cartService.getCart();
        boolean authenticated = userService.isAuthenticated();
        Phone phone = productService.getProduct(phoneId);
        // Set attributes
        model.addAttribute(CART_ATTR, cart);
        model.addAttribute(AUTHENTICATED_ATR, authenticated);
        model.addAttribute(PRODUCT_ATTR, phone);
        return "productDetailsPage";
    }
}
