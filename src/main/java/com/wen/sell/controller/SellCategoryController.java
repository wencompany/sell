package com.wen.sell.controller;

import com.wen.sell.form.ProductCategoryForm;
import com.wen.sell.pojo.ProductCategory;
import com.wen.sell.service.ProductCategoryService;
import com.wen.sell.utils.DateFormater;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/seller/category")
public class SellCategoryController {

    private static final String RETURN_INDEX_URL = "/sell/seller/category/index";
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                      Map<String, Object> map) {

        PageRequest pageRequest = new PageRequest(page - 1 , size);
        Page<ProductCategory> categoryPage = productCategoryService.findAll(pageRequest);

        map.put("categoryPage", categoryPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("category/list", map);
    }

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {

        if (null != categoryId) {
            ProductCategory category = productCategoryService.findOne(categoryId);
            map.put("category", category);
        }

        return new ModelAndView("category/index", map);
    }

    @PostMapping(value = "/save")
    public ModelAndView save(@Valid ProductCategoryForm form, BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", RETURN_INDEX_URL);
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {

            if (form.getCategoryId() != null) {
                productCategory = productCategoryService.findOne(form.getCategoryId());
                productCategory.setUpdatedTime(DateFormater.nowTimeStr());
            } else {
                productCategory.setCreatedTime(DateFormater.nowTimeStr());
                productCategory.setUpdatedTime(DateFormater.nowTimeStr());
            }

            BeanUtils.copyProperties(form, productCategory);
            productCategoryService.save(productCategory);
        } catch (Exception e) {

            map.put("msg", e.getMessage());
            map.put("url", RETURN_INDEX_URL);
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
