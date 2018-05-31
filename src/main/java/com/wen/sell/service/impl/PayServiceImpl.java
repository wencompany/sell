package com.wen.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.wen.sell.dto.OrderDTO;
import com.wen.sell.enums.ResultEnum;
import com.wen.sell.exception.SellException;
import com.wen.sell.service.OrderMasterService;
import com.wen.sell.service.PayService;
import com.wen.sell.utils.JsonUtil;
import com.wen.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信订单】 resquest={}", payRequest);

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信订单】 response={}", payResponse);

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1.验证签名
        // 2.支付的状态
        // 3.支付的金额
        // 4.支付人（下单人 == 支付人）

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);

        log.info("【微信支付 异步通知】 payResponse={}", JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderMasterService.findOne(payResponse.getOrderId());

        //判断订单是否存在
        if (null == orderDTO) {
            log.error("【微信支付】 订单不存在 orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        //校验支付金额是否一致
        if (!MathUtil.isEquals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("【微信支付】 微信返回余额与订单金额不符 responseOrderId={}, orderDTOOrderId={}, responseAmount={}, orderDTOAmount={}",
                    payResponse.getOrderId(),
                    orderDTO.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WX_BACK_AMOUNT_ERROR);
        }

        //修改订单状态
        orderMasterService.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】request={}", refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);

        log.info("【微信退款】response={}", refundResponse);

        return refundResponse;
    }
}
