package com.es.core.model.cart;

import java.io.Serializable;

public class CartItem implements Cloneable, Serializable {
    private final Long phoneId;
    private Integer quantity;

    public CartItem(Long phoneId, Integer quantity) {
        this.phoneId = phoneId;
        this.quantity = quantity;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return phoneId.intValue();
    }

    @Override
    public CartItem clone() {
        try {
            return (CartItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
