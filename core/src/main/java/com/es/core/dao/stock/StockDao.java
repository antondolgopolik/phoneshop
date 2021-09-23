package com.es.core.dao.stock;

import com.es.core.model.phone.Stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {

    Optional<Stock> get(Long phoneId);

    void update(Stock stock);

    void update(List<Stock> stocks);
}
