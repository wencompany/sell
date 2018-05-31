package com.wen.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.service.OrderMasterService;
import com.wen.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    @GetMapping(value = "/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,Map<String, Object> map) {

        //查询订单
        OrderDTO orderDTO = orderMasterService.findOne(orderId);

        if (null == orderDTO) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {

        PayResponse payResponse = payService.notify(notifyData);

        return new ModelAndView("pay/success");
    }


}
