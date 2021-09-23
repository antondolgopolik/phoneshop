package com.es.core.services.product;

import com.es.core.dao.phone.PhoneDao;
import com.es.core.dto.product.ProductDetailsDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private PhoneDao phoneDao;

    @Override
    public ProductDetailsDto getProductDetails(Long phoneId) {
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();
        productDetailsDto.setPhone(phoneDao.get(phoneId).orElseThrow());
        return productDetailsDto;
    }
}
