package com.es.core.dao.cart;

import com.es.core.model.cart.Cart;

public interface CartDao {

    Cart get();

    void update(Cart cart);
}
