package com.es.core.services.product;

import com.es.core.model.phone.Phone;

import java.util.List;

public interface ProductService {

    Phone getProduct(Long id);

    List<Phone> searchProducts(String searchQuery,
                               SortType sortType, SortDirection sortDirection,
                               int offset, int limit);

    void saveProduct(Phone phone);

    Integer getProductsInStockNumber();
}
