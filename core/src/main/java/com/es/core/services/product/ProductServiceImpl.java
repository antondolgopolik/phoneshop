package com.es.core.services.product;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.dao.phone.SearchQueryBuilder;
import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private PhoneDao phoneDao;

    @Override
    public Phone getProduct(Long id) {
        return phoneDao
                .get(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with given id doesn't exist!"));
    }

    @Override
    public List<Phone> searchProducts(String searchQuery,
                                      SortType sortType, SortDirection sortDirection,
                                      int offset, int limit) {
        // Prepare builder
        SearchQueryBuilder searchQueryBuilder = new SearchQueryBuilder();
        searchQueryBuilder.setSearchQuery(searchQuery);
        searchQueryBuilder.setSortType(sortType);
        searchQueryBuilder.setSortDirection(sortDirection);
        searchQueryBuilder.setOffset(offset);
        searchQueryBuilder.setLimit(limit);
        // Search
        return phoneDao.search(searchQueryBuilder);
    }

    @Override
    public void saveProduct(Phone product) {
        phoneDao.save(product);
    }

    @Override
    public Integer getProductsInStockNumber() {
        return phoneDao.getPhonesInStockNumber();
    }
}
