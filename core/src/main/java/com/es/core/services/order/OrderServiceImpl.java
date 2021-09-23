package com.es.core.services.order;

import com.es.core.dao.order.OrderDao;
import com.es.core.dao.orderitem.OrderItemDao;
import com.es.core.dao.phone.PhoneDao;
import com.es.core.dto.cart.CartItemDto;
import com.es.core.dto.order.OrderItemDto;
import com.es.core.model.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import com.es.core.services.cart.CartService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private CartService cartService;

    @Override
    public List<Order> getOrders() {
        return orderDao.get();
    }

    @Override
    public Order getOrder(String orderId) {
        return orderDao
                .get(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order with given id doesn't exist!"));
    }

    @Override
    public Collection<OrderItemDto> getOrderItems(String orderId) {
        return orderItemDao.get(orderId).stream().map(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderId(orderItem.getOrderId());
            orderItemDto.setPhone(phoneDao.get(orderItem.getPhoneId()).orElseThrow());
            orderItem.setQuantity(orderItem.getQuantity());
            return orderItemDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Order createOrder(String username,
                             Cart cart,
                             BigDecimal deliveryPrice,
                             Collection<CartItemDto> cartItems,
                             String firstName, String lastName,
                             String address,
                             String phoneNumber,
                             String additionalInfo) {
        // Start transaction
        return transactionTemplate.execute(transactionStatus -> {
            // Prepare
            Order order = new Order();
            order.setUsername(username);
            order.setDate(new Date());
            order.setTotalQuantity(cart.getTotalQuantity());
            order.setSubtotal(cart.getSubtotal());
            order.setDeliveryPrice(deliveryPrice);
            order.setTotalPrice(cart.getSubtotal().add(deliveryPrice));
            order.setFirstName(firstName);
            order.setLastName(lastName);
            order.setAddress(address);
            order.setPhoneNumber(phoneNumber);
            order.setAdditionalInfo(additionalInfo);
            order.setStatus(OrderStatus.NEW);
            // Start unique order ID generating
            boolean saved = false;
            while (!saved) {
                try {
                    // Generate order ID
                    String orderId = UUID.randomUUID().toString();
                    // Update order
                    order.setId(orderId);
                    // Try to save order
                    orderDao.save(order);
                    // Success
                    saved = true;
                } catch (DuplicateKeyException ignored) {
                }
            }
            // Prepare order items
            List<OrderItem> orderItems = cartItems.stream().map(cartItemDto -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getId());
                orderItem.setPhoneId(cartItemDto.getPhone().getId());
                orderItem.setQuantity(cartItemDto.getQuantity());
                return orderItem;
            }).collect(Collectors.toList());
            // Save order items
            orderItemDao.save(orderItems);
            // Order cart
            cartService.orderCart();
            // Return
            return order;
        });
    }

    @Override
    public void orderDelivered(String orderId) {
        // Change status
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.DELIVERED);
        // Save update
        orderDao.updateStatus(order);
    }

    @Override
    public void orderRejected(String orderId) {
        // Change status
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.REJECTED);
        // Save update
        orderDao.updateStatus(order);
    }
}
