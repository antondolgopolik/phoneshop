package com.es.core.services.cart;

import com.es.core.dto.cart.CartAdditionDto;
import com.es.core.dto.cart.CartItemDto;
import com.es.core.model.cart.Cart;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CartService{

    Cart getCart();

    CartItemDto getCartItem(Long phoneId);

    Collection<CartItemDto> getCartItems();

    void addToCart(Long phoneId, Integer quantity);

    void addAllToCart(List<CartAdditionDto> cartAdditions);

    void updateCart(Long phoneId, Integer quantity);

    void updateCart(Map<Long, Integer> updates);

    void deleteFromCart(Long phoneId);

    void orderCart();
}
