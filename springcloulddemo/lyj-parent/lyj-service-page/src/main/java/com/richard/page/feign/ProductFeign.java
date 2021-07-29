package com.richard.page.feign;

import com.richard.pojo.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "lyj-service-product", fallback = ProductFeignFallBack.class)
public interface ProductFeign {

    @GetMapping("/product/query/{id}")
    public Products getProduct(@PathVariable Integer id);

    @GetMapping("/service/port")
    public String getPort();

}
