package com.es.core.dao.stock;

import com.es.core.model.phone.Stock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcStockDao implements StockDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Stock> get(Long phoneId) {
        String sql = "SELECT * FROM stocks WHERE phone_id=?";
        Stock stock = jdbcTemplate.query(sql, new SingleStockResultSetExtractor(), phoneId);
        return Optional.ofNullable(stock);
    }

    @Override
    public void update(Stock stock) {
        String sql = "UPDATE stocks SET stock=?, reserved=? WHERE phone_id=?";
        jdbcTemplate.update(sql, stock.getStock(), stock.getReserved(), stock.getPhoneId());
    }

    @Override
    public void update(List<Stock> stocks) {
        // Prepare args
        List<Object[]> args = new ArrayList<>(stocks.size());
        for (Stock stock : stocks) {
            args.add(new Object[]{stock.getStock(), stock.getReserved(), stock.getPhoneId()});
        }
        // Update
        String sql = "UPDATE stocks SET stock=?, reserved=? WHERE phone_id=?";
        jdbcTemplate.batchUpdate(sql, args);
    }
}
