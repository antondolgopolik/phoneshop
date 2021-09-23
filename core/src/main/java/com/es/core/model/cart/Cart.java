package com.es.core.model.cart;

import java.math.BigDecimal;

public class Cart implements Cloneable {
    private BigDecimal subtotal = new BigDecimal(0);
    private int totalQuantity;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public Cart clone() {
        try {
            return (Cart) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
