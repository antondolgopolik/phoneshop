package com.es.core.model.phone;

import java.util.List;
import java.util.Optional;

public interface PhoneDao {

    Optional<Phone> get(Long id);

    void save(Phone phone);

    List<Phone> findAllInStock(String request, PhoneSortType phoneSortType, SortDirection sortDirection, int offset, int limit);

    int getPhonesInStockNumber();
}
