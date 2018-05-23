package com.wen.sell.controller;

import com.wen.sell.convert.OrderForm2OrderDTOConvert;
import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.form.OrderForm;
import com.wen.sell.service.BuyerService;
import com.wen.sell.service.OrderMasterService;
import com.wen.sell.utils.ResultVoUtil;
import com.wen.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping(value = "/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确 orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO orderDTOTemp = orderMasterService.createOrder(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDTOTemp.getOrderId());

        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping(value = "/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【订单列表】openid为空 opendi={}", openid);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(openid, pageRequest);

        return ResultVoUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping(value = "detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultVoUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping(value = "/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

}
