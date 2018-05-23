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

    //TODO +库存
    void increaseStock(List<CartDTO> cartDTOS);

    //TODO -库存
    void decreaseStock(List<CartDTO> cartDTOS);
}
