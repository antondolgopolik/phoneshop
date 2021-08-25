package com.es.core.model.stock;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class JdbcStockDao implements StockDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private PhoneDao phoneDao;

    @Override
    public Optional<Stock> getByPhoneId(Long phoneId) {
        Optional<Phone> phone = phoneDao.get(phoneId);
        if (phone.isEmpty()) {
            throw new IllegalArgumentException("Phone with given ID doesn't exist!");
        } else {
            return getByPhone(phone.get());
        }
    }

    @Override
    public Optional<Stock> getByPhone(Phone phone) {
        String sql = "SELECT * FROM stocks WHERE phoneId=?";
        Stock stock = jdbcTemplate.query(sql, new SingleStockResultSetExtractor(phone), phone.getId());
        return Optional.ofNullable(stock);
    }
}
