package com.wen.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "买家姓名不能为空")
    private String name;

    @NotEmpty(message = "买家电话号码不能为空")
    private String phone;

    @NotEmpty(message = "买家地址不能为空")
    private String address;

    @NotEmpty(message = "买家微信openid不能为空")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
