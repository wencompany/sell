package com.wen.sell.form;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductInfoForm {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;
}
