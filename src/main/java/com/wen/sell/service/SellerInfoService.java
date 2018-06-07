package com.wen.sell.service;

import com.wen.sell.pojo.SellerInfo;

public interface SellerInfoService {
    SellerInfo findSellerByOpenid(String openid);
}
