package com.es.core.dao.order;

import com.es.core.model.order.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOrderDao implements OrderDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> get() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    @Override
    public Optional<Order> get(String id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = jdbcTemplate.query(sql, new SingleOrderResultSetExtractor(), id);
        return Optional.ofNullable(order);
    }

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = new Object[]{
                order.getId(), order.getUsername(), order.getDate(), order.getTotalQuantity(),
                order.getSubtotal(), order.getDeliveryPrice(), order.getTotalPrice(),
                order.getFirstName(), order.getLastName(), order.getAddress(),
                order.getPhoneNumber(), order.getAdditionalInfo(), order.getStatus().ordinal()
        };
        int[] argTypes = new int[]{
                Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.INTEGER, Types.FLOAT, Types.FLOAT,
                Types.FLOAT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.TINYINT
        };
        jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public void updateStatus(Order order) {
        String sql = "UPDATE orders SET status=? WHERE id=?";
        jdbcTemplate.update(sql, order.getStatus().ordinal(), order.getId());
    }
}
