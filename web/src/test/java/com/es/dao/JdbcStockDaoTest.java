package com.es.dao;

import com.es.core.dao.stock.JdbcStockDao;
import com.es.core.model.phone.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JdbcStockDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private JdbcStockDao jdbcStockDao;

    @Test
    public void updateTest() {
        List<Stock> stocks = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Stock stock = new Stock();
            stock.setStock(i);
            stock.setReserved(i);
            stock.setPhoneId((long) i);
            stocks.add(stock);
        }
        jdbcStockDao.update(stocks);
        verify(jdbcTemplate, never()).batchUpdate(anyString(), anyList());
    }
}
