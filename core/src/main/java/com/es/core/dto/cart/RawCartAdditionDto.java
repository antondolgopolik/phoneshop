package com.es.core.dto.cart;

public class RawCartAdditionDto {
    private String model;
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
