package com.wen.sell.dao;

import com.wen.sell.pojo.ProductCategory;
import com.wen.sell.utils.DateFormater;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void testFindOne() {
        //ProductCategory category = repository.getOne(1);
//        List<ProductCategory> categories = repository.findAll();
        ProductCategory category = repository.findOne(1);
        System.out.printf(category.toString());

    }

    @Test
    public void testSave() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("热销");
        category.setCategoryType(2);
        category.setCreatedTime(DateFormater.nowTimeStr());
        category.setUpdatedTime(DateFormater.nowTimeStr());
        ProductCategory save = repository.save(category);
        System.out.printf(save.toString());
    }

    @Test
    public void testFindAll() {
        List<ProductCategory> categories = repository.findAll();
        Assert.assertNotEquals(0, categories.size());
    }

    @Test
    public void testFindByCategoryType() {
        List<Integer> types = new ArrayList<>();
        types.add(1);
        types.add(2);
        List<ProductCategory> categories = repository.findByCategoryTypeIn(types);
        for (ProductCategory category: categories) {
            System.out.println(category.toString());
        }
    }
}