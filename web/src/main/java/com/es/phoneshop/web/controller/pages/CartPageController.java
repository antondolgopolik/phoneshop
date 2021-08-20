package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.cart.CartDao;
import com.es.core.dao.cartitem.CartItemDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;
import com.es.core.model.phone.Phone;
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
    private CartDao cartDao;
    @Resource
    private CartItemDao cartItemDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showCart(Model model) {
        Cart cart = cartDao.get();
        Collection<Phone> phones = cartItemDao.getPhones();
        model.addAttribute("cart", cart);
        model.addAttribute("phones", phones);
        return "cart";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCart() {

    }
}
