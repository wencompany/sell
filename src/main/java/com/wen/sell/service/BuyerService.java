package com.wen.sell.service;

import com.wen.sell.dto.OrderDTO;

public interface BuyerService {

    /**
     * 当前用户查找自己的订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOneOrder(String openid, String orderId);

    /**
     * 当前用户取消自己的订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
