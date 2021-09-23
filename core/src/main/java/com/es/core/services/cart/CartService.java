package com.es.core.services.cart;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.model.cart.Cart;

import java.util.Collection;
import java.util.Map;

public interface CartService{

    Cart getCart();

    CartItemDto getCartItem(Long phoneId);

    Collection<CartItemDto> getCartItems();

    void add(Long phoneId, Integer quantity);

    void update(Long phoneId, Integer quantity);

    void update(Map<Long, Integer> updates);

    void delete(Long phoneId);
}
