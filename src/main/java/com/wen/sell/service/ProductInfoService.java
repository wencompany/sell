package com.wen.sell.service;

import com.wen.sell.dto.CartDTO;
import com.wen.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findPage(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // +库存
    void increaseStock(List<CartDTO> cartDTOS);

    // -库存
    void decreaseStock(List<CartDTO> cartDTOS);

    /**
     * 上架商品
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}
