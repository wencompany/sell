package com.wen.sell.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductVo {

    private String name;

    private Integer type;

    private List<ProductInfoVo> foods;
}
