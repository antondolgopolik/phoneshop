package com.es.core.services.product;

import com.es.core.dto.product.ProductDetailsDto;

public interface ProductService {

    ProductDetailsDto getProductDetails(Long phoneId);
}
