package com.es.core.dao.order;

import com.es.core.model.order.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    private final OrderExtractor orderExtractor = new OrderExtractor();

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        return orderExtractor.extract(resultSet);
    }
}
