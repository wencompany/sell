package com.wen.sell.service.impl;

import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.service.BuyerService;
import com.wen.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {

        return checkUserOrder(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {

        OrderDTO orderDTO = checkUserOrder(openid, orderId);

        if (null == orderDTO) {
            log.error("【取消订单】 订单不存在的 orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        return orderMasterService.cancel(orderDTO);
    }

    private OrderDTO checkUserOrder(String openid, String orderId) {

        OrderDTO orderDTO = orderMasterService.findOne(orderId);

        if (null == orderDTO) {
            return null;
        }

        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查找订单】 非用户本人操作 openid={}", openid);
            throw new SellException(ResultEnum.ORDER_USER_ERROR);
        }

        return orderDTO;
    }
}
