package com.wen.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新下单"),
    FINISHED(1, "完成订单"),
    CANCEL(2, "取消订单");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
