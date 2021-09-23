package com.es.phoneshop.web.controller.pages;

import com.es.core.dto.product.ProductDetailsDto;
import com.es.core.model.cart.Cart;
import com.es.core.services.cart.CartService;
import com.es.core.services.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    private static final String PRODUCT_DETAILS_ATTR = "productDetails";
    private static final String CART_ATTR = "cart";

    @Resource
    private ProductService productService;
    @Resource
    private CartService cartService;

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.GET)
    public String showProductDetails(Model model,
                                     @PathVariable Long phoneId) {
        ProductDetailsDto productDetails = productService.getProductDetails(phoneId);
        Cart cart = cartService.getCart();
        // Set attributes
        model.addAttribute(PRODUCT_DETAILS_ATTR, productDetails);
        model.addAttribute(CART_ATTR, cart);
        return "productDetailsPage";
    }
}
