package com.wen.sell.controller;

import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @GetMapping(value = "/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        OrderDTO orderDTO = orderMasterService.findOne(orderId);

        if (null == orderDTO) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }



    }
}
