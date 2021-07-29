package com.richard.product.controller;

import com.richard.pojo.Products;
import com.richard.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/query/{id}")
    public Products getProduct(@PathVariable Integer id) {
        Products products = productService.queryById(id);
        return products;
    }



}
