package com.es.core.dao.orderitem;

import com.es.core.model.order.OrderItem;

import java.util.Collection;
import java.util.List;

public interface OrderItemDao {

    List<OrderItem> get(String orderId);

    void save(Collection<OrderItem> orderItems);
}
