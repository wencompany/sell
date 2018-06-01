package com.wen.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wen.sell.enums.OrderStatusEnum;
import com.wen.sell.enums.PayStatusEnum;
import com.wen.sell.pojo.OrderDetail;
import com.wen.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private String createdTime;

    private String updatedTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getEnum(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getEnum(payStatus, PayStatusEnum.class);
    }
}
