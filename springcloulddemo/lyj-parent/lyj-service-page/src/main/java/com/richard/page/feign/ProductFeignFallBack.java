package com.richard.page.feign;

import com.richard.pojo.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallBack implements ProductFeign{
    @Override
    public Products getProduct(Integer id) {
        return null;
    }

    @Override
    public String getPort() {
        return "-1";
    }
}
