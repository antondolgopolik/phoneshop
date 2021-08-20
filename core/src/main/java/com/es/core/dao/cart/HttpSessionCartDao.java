package com.es.core.dao.cart;

import com.es.core.model.cart.Cart;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Component
public class HttpSessionCartDao implements CartDao {
    private static final String CART_ATTR = HttpSessionCartDao.class.getName() + ".cart";

    @Resource
    private HttpSession httpSession;

    @Override
    public Cart get() {
        Cart cart = (Cart) httpSession.getAttribute(CART_ATTR);
        if (cart == null) {
            cart = new Cart();
            httpSession.setAttribute(CART_ATTR, cart);
        }
        return cart;
    }

    @Override
    public void update(Cart cart) {
        httpSession.setAttribute(CART_ATTR, cart);
    }
}
