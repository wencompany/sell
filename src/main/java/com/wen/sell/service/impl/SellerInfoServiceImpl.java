package com.wen.sell.service.impl;

import com.wen.sell.dao.SellerInfoRepository;
import com.wen.sell.pojo.SellerInfo;
import com.wen.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerByOpenid(String openid) {

        return sellerInfoRepository.findByOpenid(openid);
    }
}
