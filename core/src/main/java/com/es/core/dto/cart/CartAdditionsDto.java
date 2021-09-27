package com.es.core.dto.cart;

import java.util.Map;

public class CartAdditionsDto {
    private Map<Integer, CartAdditionDto> additions;

    public Map<Integer, CartAdditionDto> getAdditions() {
        return additions;
    }

    public void setAdditions(Map<Integer, CartAdditionDto> additions) {
        this.additions = additions;
    }
}
