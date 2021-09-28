package com.es.core.dto.cart;

import javax.validation.constraints.Positive;

public class CartAdditionDto {
    private String model;
    @Positive(message = "Quantity should be positive integer")
    private Integer quantity;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
