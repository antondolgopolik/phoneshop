package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {

    Optional<Phone> get(Long id);

    List<Phone> search(SearchQueryBuilder searchQueryBuilder);

    void save(Phone phone);

    Integer getPhonesInStockNumber();
}
