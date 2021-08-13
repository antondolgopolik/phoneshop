package com.es.core.model.stock;

import com.es.core.model.phone.Phone;

import java.util.Optional;

public interface StockDao {

    Optional<Stock> getByPhoneId(Long phoneId);

    Optional<Stock> getByPhone(Phone phone);
}
