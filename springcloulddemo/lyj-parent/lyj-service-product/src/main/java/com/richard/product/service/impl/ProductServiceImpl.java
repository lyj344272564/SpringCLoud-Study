package com.richard.product.service.impl;

import com.richard.pojo.Products;
import com.richard.product.mapper.ProductMapper;
import com.richard.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Products queryById(Integer id) {
        return productMapper.selectById(id);
    }
}
