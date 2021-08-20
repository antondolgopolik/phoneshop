package com.es.core.dao.cartitem;

import com.es.core.model.cart.CartItem;
import com.es.core.model.phone.Phone;

import java.util.Collection;
import java.util.Optional;

public interface CartItemDao {

    Collection<CartItem> getCartItems();

    Collection<Phone> getPhones();

    Optional<CartItem> get(Long phoneId);

    void save(CartItem cartItem);

    void update(CartItem cartItem);
}
