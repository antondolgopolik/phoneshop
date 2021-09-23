package com.es.core.model.order;

public enum OrderStatus {
    NEW("New"), DELIVERED("Delivered"), REJECTED("Rejected");

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return orderStatus;
    }
}
