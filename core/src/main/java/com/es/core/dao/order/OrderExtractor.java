package com.es.core.dao.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderExtractor {

    public Order extract(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getString(1));
        order.setUsername(resultSet.getString(2));
        order.setDate(resultSet.getDate(3));
        order.setTotalQuantity(resultSet.getInt(4));
        order.setSubtotal(resultSet.getBigDecimal(5));
        order.setDeliveryPrice(resultSet.getBigDecimal(6));
        order.setTotalPrice(resultSet.getBigDecimal(7));
        order.setFirstName(resultSet.getString(8));
        order.setLastName(resultSet.getString(9));
        order.setAddress(resultSet.getString(10));
        order.setPhoneNumber(resultSet.getString(11));
        order.setAdditionalInfo(resultSet.getString(12));
        order.setStatus(OrderStatus.values()[resultSet.getInt(13)]);
        return order;
    }
}
