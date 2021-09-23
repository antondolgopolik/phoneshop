package com.es.core.dao.order;

import com.es.core.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    List<Order> get();

    Optional<Order> get(String id);

    void save(Order order);

    void updateStatus(Order order);
}
