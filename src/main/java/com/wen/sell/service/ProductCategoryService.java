package com.wen.sell.service;

import com.wen.sell.pojo.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory productCategory);

    Page<ProductCategory> findAll(Pageable pageable);
}
