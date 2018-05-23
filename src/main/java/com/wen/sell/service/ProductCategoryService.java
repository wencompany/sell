package com.wen.sell.service;

import com.wen.sell.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory productCategory);
}
