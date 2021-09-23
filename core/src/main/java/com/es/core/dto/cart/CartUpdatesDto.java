package com.es.core.dto.cart;

import java.util.Map;

public class CartUpdatesDto {
    private Map<Long, Integer> updates;

    public Map<Long, Integer> getUpdates() {
        return updates;
    }

    public void setUpdates(Map<Long, Integer> updates) {
        this.updates = updates;
    }
}
