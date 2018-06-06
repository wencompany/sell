package com.wen.sell.controller;

import com.wen.sell.form.ProductInfoForm;
import com.wen.sell.pojo.ProductCategory;
import com.wen.sell.pojo.ProductInfo;
import com.wen.sell.service.ProductCategoryService;
import com.wen.sell.service.ProductInfoService;
import com.wen.sell.utils.DateFormater;
import com.wen.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellProductController {

    private static final String RETURN_URL = "/sell/seller/product/list";
    private static final String RETURN_INDEX_URL = "/sell/seller/product/index";

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1 , size);
        Page<ProductInfo> productInfos = productInfoService.findPage(request);

        map.put("productInfos", productInfos);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("product/list", map);

    }

    @GetMapping(value = "/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {

        try {

            ProductInfo p = productInfoService.onSale(productId);
        } catch (Exception e) {
            log.error("【商品上架】 发生异常{}", e.getMessage());

            map.put("url", RETURN_URL);

            return new ModelAndView("common/error", map);
        }

        map.put("url", RETURN_URL);
        return new ModelAndView("common/success", map);
    }

    @GetMapping(value = "/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {

        try {

            ProductInfo p = productInfoService.offSale(productId);
        } catch (Exception e) {
            log.error("【商品上架】 发生异常{}", e.getMessage());

            map.put("url", RETURN_URL);

            return new ModelAndView("common/error", map);
        }

        map.put("url", RETURN_URL);
        return new ModelAndView("common/success", map);
    }

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        List<ProductCategory> categoryList = categoryService.findAll();

        map.put("categoryList", categoryList);

        return  new ModelAndView("product/index", map);

    }

    @PostMapping(value = "/save")
    public ModelAndView save(@Valid ProductInfoForm form, BindingResult bindingResult,
                             Map<String, Object> map) {

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", RETURN_INDEX_URL);

            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();

        try {
            if (!StringUtils.isEmpty(form.getProductId())) {

                productInfo = productInfoService.findOne(form.getProductId());
                productInfo.setUpdatedTime(DateFormater.nowTimeStr());
            } else {
                form.setProductId(KeyUtil.getUniqueKey());
                productInfo.setCreatedTime(DateFormater.nowTimeStr());
                productInfo.setUpdatedTime(DateFormater.nowTimeStr());
            }

            BeanUtils.copyProperties(form, productInfo);
            productInfoService.save(productInfo);

        } catch (Exception e) {

            map.put("msg", e.getMessage());
            map.put("url", RETURN_INDEX_URL);

            return new ModelAndView("common/error", map);
        }

        map.put("url", RETURN_URL);

        return new ModelAndView("common/success", map);
    }
}
