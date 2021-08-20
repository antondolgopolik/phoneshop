package com.es.core.model.cart;

import java.math.BigDecimal;

public class Cart {
    private BigDecimal totalCost = new BigDecimal(0);
    private int totalQuantity;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
