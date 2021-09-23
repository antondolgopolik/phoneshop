package com.es.core.dao.phone;

import com.es.core.model.phone.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {

    Optional<Phone> get(Long id);

    void save(Phone phone);

    List<Phone> findInStock(String request, PhoneSortType phoneSortType, SortDirection sortDirection, int offset, int limit);

    Integer getPhonesInStockNumber();
}
