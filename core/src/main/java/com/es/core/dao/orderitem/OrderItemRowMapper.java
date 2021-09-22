package com.es.core.dao.orderitem;

import com.es.core.model.order.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(resultSet.getString(1));
        orderItem.setPhoneId(resultSet.getLong(2));
        orderItem.setQuantity(resultSet.getInt(3));
        return orderItem;
    }
}
