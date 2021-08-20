package com.es.core.dao.stock;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.Stock;

import java.util.Optional;

public interface StockDao {

    Optional<Stock> getByPhoneId(Long phoneId);

    Optional<Stock> getByPhone(Phone phone);

    void update(Stock stock);
}
