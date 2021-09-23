package com.es.core.dto.cart;

import com.es.core.model.phone.Phone;

import java.math.BigDecimal;

public class CartItemDto {
    private Phone phone;
    private Integer quantity;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return phone.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
