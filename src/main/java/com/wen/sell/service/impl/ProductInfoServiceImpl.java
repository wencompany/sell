package com.wen.sell.service.impl;

import com.wen.sell.dao.ProductInfoRepository;
import com.wen.sell.dto.CartDTO;
import com.wen.sell.enums.ProductStatusEnum;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.pojo.ProductInfo;
import com.wen.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findPage(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO : cartDTOS) {

            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());

            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            int stock = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(stock);

            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO : cartDTOS) {

            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());

            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            int stock = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (stock < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(stock);

            productInfoRepository.save(productInfo);

        }
    }
}
