package com.es.core.services.order;

import com.es.core.dto.cart.CartItemDto;
import com.es.core.dto.order.OrderItemDto;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    Order getOrder(String orderId);

    Collection<OrderItemDto> getOrderItems(String orderId);

    Order createOrder(String username,
                      Cart cart,
                      BigDecimal deliveryPrice,
                      Collection<CartItemDto> cartItems,
                      String firstName, String lastName,
                      String address,
                      String phoneNumber,
                      String additionalInfo);

    void orderDelivered(String orderId);

    void orderRejected(String orderId);
}
