package com.es.core.dao.order;

import com.es.core.model.order.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleOrderResultSetExtractor implements ResultSetExtractor<Order> {
    private final OrderExtractor orderExtractor = new OrderExtractor();

    @Override
    public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Order order = null;
        if (resultSet.next()) {
            order = orderExtractor.extract(resultSet);
        }
        return order;
    }
}
