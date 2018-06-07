package com.wen.sell.controller;

import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.service.OrderMasterService;
import com.wen.sell.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(value = "/seller/order")
@Slf4j
public class SellOrderController {

    private static final String RETURN_URL = "/sell/seller/order/list";

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PushMessage pushMessage;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String, Object> map) {

        PageRequest pageRequest = new PageRequest(page-1, size);

        Page<OrderDTO> orderDTOPage = orderMasterService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("cancel")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        try {

            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderMasterService.cancel(orderDTO);

        } catch (Exception e) {

            log.error("【卖家取消订单】 异常 {}", e);

            map.put("msg", e.getMessage());
            map.put("url", RETURN_URL);

            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", RETURN_URL);

        return new ModelAndView("common/success", map);

    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderMasterService.findOne(orderId);
        } catch (Exception e) {
            log.error("【卖家订单详情】 发生异常{}", e);

            map.put("msg", e.getMessage());
            map.put("url", RETURN_URL);

            return new ModelAndView("common/error",map);
        }

        map.put("orderDTO", orderDTO);

        return new ModelAndView("order/detail", map);

    }

    @GetMapping("finishi")
    public ModelAndView finishi(@RequestParam("orderId") String orderId,
                                Map<String, Object> map) {

        OrderDTO orderDTOTemp = new OrderDTO();
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            orderDTOTemp = orderMasterService.finish(orderDTO);
        } catch (Exception e) {
            log.error("【卖家完结订单】 发生异常 {}", e);

            map.put("msg", e.getMessage());
            map.put("url", RETURN_URL);

            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_FINISHI_SUCCESS.getMsg());
        map.put("url", RETURN_URL);

        pushMessage.orderStatus(orderDTOTemp);

        return new ModelAndView("common/error", map);

    }
}
