package com.es.phoneshop.web.controller.pages;

import com.es.core.dao.cart.CartDao;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.cart.Cart;
import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private CartDao cartDao;

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.GET)
    public String showProductDetails(Model model,
                                     @PathVariable Long phoneId) {
        Optional<Phone> phoneOptional = phoneDao.get(phoneId);
        Phone phone = phoneOptional.orElseThrow();
        Cart cart = cartDao.get();
        model.addAttribute("phone", phone);
        model.addAttribute("cart", cart);
        return "productDetails";
    }
}
