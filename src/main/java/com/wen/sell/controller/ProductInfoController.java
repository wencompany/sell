package com.wen.sell.controller;

import com.wen.sell.pojo.ProductCategory;
import com.wen.sell.pojo.ProductInfo;
import com.wen.sell.service.ProductCategoryService;
import com.wen.sell.service.ProductInfoService;
import com.wen.sell.utils.ResultVoUtil;
import com.wen.sell.vo.ProductInfoVo;
import com.wen.sell.vo.ProductVo;
import com.wen.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/buyer/product")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo getList(){

        List<ProductInfo> productInfos = productInfoService.findUpAll();

        List<Integer> categoryTypes = new ArrayList<>();
        for (ProductInfo productInfo : productInfos) {
            categoryTypes.add(productInfo.getCategoryType());
        }

        List<ProductCategory> categories = productCategoryService.findByCategoryTypeIn(categoryTypes);

        List<ProductVo> productVos = new ArrayList<>();
        for (ProductCategory category : categories) {
            ProductVo productVo = new ProductVo();
            productVo.setName(category.getCategoryName());
            productVo.setType(category.getCategoryType());

            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVo.setFoods(productInfoVos);
            productVos.add(productVo);
        }
        return ResultVoUtil.success(productVos);
    }
}
