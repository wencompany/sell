package com.wen.sell.service;

import com.wen.sell.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);
}
