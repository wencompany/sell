package com.wen.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIT(10, "商品不存在哦"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误了额"),
    ORDER_NOT_EXIT(12, "没有订单额"),
    ORDER_DETAIL_NOT_EXIT(13, "订单详情不存在的"),
    ORDER_STATUS_ERROR(14, "订单状态不正确的"),
    ORDER_UPDATE_FAIL(15, "订单更新失败了额"),
    ORDER_DETAIL_EMPTY(16, "暂无订单详情哦"),
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态错误了"),
    CART_EMPTY(18, "购物车不能为空啊"),
    ORDER_USER_ERROR(19, "不是你的订单查它干嘛"),
    WECHAT_MP_ERROR(20, "微信公众号方面错误"),
    WX_BACK_AMOUNT_ERROR(21, "微信返回金额不符"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISHI_SUCCESS(23, "订单完结成功"),
    PRODUCT_STATUS_ERROR(24, "商品状态错误"),
    LOGIN_ERROR(25, "登录参数不正确"),
    LOGOUT_SUCCESS(26, "退出成功")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
