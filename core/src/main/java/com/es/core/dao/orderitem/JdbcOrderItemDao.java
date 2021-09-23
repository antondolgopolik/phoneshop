package com.es.core.dao.orderitem;

import com.es.core.model.order.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class JdbcOrderItemDao implements OrderItemDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderItem> get(String orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        return jdbcTemplate.query(sql, new OrderItemRowMapper(), orderId);
    }

    @Override
    public void save(Collection<OrderItem> orderItems) {
        // Prepare args
        List<Object[]> args = new ArrayList<>(orderItems.size());
        for (OrderItem orderItem : orderItems) {
            args.add(new Object[]{orderItem.getOrderId(), orderItem.getPhoneId(), orderItem.getQuantity()});
        }
        // Update
        String sql = "INSERT INTO order_items VALUES(?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, args);
    }
}
