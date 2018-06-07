package com.wen.sell.service;

import com.wen.sell.dto.OrderDTO;

public interface PushMessage {

    /**
     * 订单状态
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
