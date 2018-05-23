package com.wen.sell.controller;

import com.wen.sell.pojo.ProductCategory;
import com.wen.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getCategories")
    public List<ProductCategory> getCategories() {
        return productCategoryService.findAll();
    }
}
