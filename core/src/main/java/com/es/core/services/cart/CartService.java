package com.es.core.services.cart;

import com.es.core.model.phone.Phone;

import java.util.List;

public interface CartService {

    void add(Long phoneId, Integer quantity);

    void update(Long phoneId, Integer quantity);

    void remove(Long phoneId);
}
