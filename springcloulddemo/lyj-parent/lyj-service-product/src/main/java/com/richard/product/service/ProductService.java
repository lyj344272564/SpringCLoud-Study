package com.richard.product.service;

import com.richard.pojo.Products;

public interface ProductService {

    /**
     * 通过商品id查询
     * @param id
     * @return 商品信息
     */
    Products queryById(Integer id);

}
