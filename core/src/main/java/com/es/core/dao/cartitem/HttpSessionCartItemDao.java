package com.es.core.dao.cartitem;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.model.cart.CartItem;
import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HttpSessionCartItemDao implements CartItemDao {
    private static final String CART_ITEMS_ATTR = HttpSessionCartItemDao.class.getName() + ".items";
    private Map<Long, CartItem> cartItemsMap;

    @Resource
    private HttpSession httpSession;
    @Resource
    private PhoneDao phoneDao;

    private Map<Long, CartItem> getCartItemsMap() {
        if (cartItemsMap == null) {
            //noinspection unchecked
            cartItemsMap = (Map<Long, CartItem>) httpSession.getAttribute(CART_ITEMS_ATTR);
            if (cartItemsMap == null) {
                cartItemsMap = new HashMap<>();
                httpSession.setAttribute(CART_ITEMS_ATTR, cartItemsMap);
            }
        }
        return cartItemsMap;
    }

    @Override
    public Collection<CartItem> getCartItems() {
        return getCartItemsMap().values();
    }

    @Override
    public Collection<Phone> getPhones() {
        Collection<CartItem> cartItems = getCartItems();
        return cartItems.parallelStream()
                .map(cartItem -> phoneDao.get(cartItem.getPhoneId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartItem> get(Long phoneId) {
        return Optional.ofNullable(getCartItemsMap().get(phoneId));
    }

    @Override
    public void save(CartItem cartItem) {
        getCartItemsMap().put(cartItem.getPhoneId(), cartItem);
    }

    @Override
    public void update(CartItem cartItem) {
        getCartItemsMap().put(cartItem.getPhoneId(), cartItem);
    }
}
