package com.wen.sell.service.impl;

import com.wen.sell.dao.ProductInfoRepository;
import com.wen.sell.dto.CartDTO;
import com.wen.sell.enums.ProductStatusEnum;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.pojo.ProductInfo;
import com.wen.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
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

    @Override
    public ProductInfo onSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);

        if (null == productInfo) {
            log.error("【上架商品】 productId={}", productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        if (productInfo.getProductStatusEnum().getMsg().equals(ProductStatusEnum.UP.getMsg())) {
            log.error("【上架商品】 商品状态不正确 product={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {

        ProductInfo productInfo = productInfoRepository.findOne(productId);

        if (null == productInfo) {
            log.error("【下架商品】 productId={}", productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
        }

        if (productInfo.getProductStatusEnum().getMsg().equals(ProductStatusEnum.DOWN.getMsg())) {
            log.error("【下架商品】 商品状态不正确 product={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }
}
