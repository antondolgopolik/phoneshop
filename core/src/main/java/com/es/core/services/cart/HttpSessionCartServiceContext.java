package com.es.core.services.cart;

import com.es.core.model.cart.Cart;
import com.es.core.model.cart.CartItem;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpSessionCartServiceContext {
    private final Cart cart;
    private final HashMap<Long, CartItem> cartItemsMap;

    public HttpSessionCartServiceContext(Cart cart, HashMap<Long, CartItem> cartItemHashMap) {
        this.cart = cart;
        this.cartItemsMap = cartItemHashMap;
    }

    public Cart getCart() {
        return cart;
    }

    public Map<Long, CartItem> getCartItemsMap() {
        return Collections.unmodifiableMap(cartItemsMap);
    }

    public void resetContext() {
        cart.setTotalQuantity(0);
        cart.setSubtotal(new BigDecimal(0));
        cartItemsMap.clear();
    }

    public void updateContext(HttpSessionCartServiceContext context) {
        updateCart(context.cart);
        updateCartItemsMap(context.cartItemsMap);
    }

    public void updateCartItemsMap(HashMap<Long, CartItem> newCartItemsMap) {
        cartItemsMap.clear();
        cartItemsMap.putAll(newCartItemsMap);
    }

    public void updateCart(Cart newCart) {
        cart.setTotalQuantity(newCart.getTotalQuantity());
        cart.setSubtotal(newCart.getSubtotal());
    }

    public void putCartItem(CartItem cartItem) {
        cartItemsMap.put(cartItem.getPhoneId(), cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItemsMap.remove(cartItem.getPhoneId());
    }

    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    @Override
    public HttpSessionCartServiceContext clone() {
        return new HttpSessionCartServiceContext(cart.clone(), (HashMap<Long, CartItem>) cartItemsMap.clone());
    }
}
