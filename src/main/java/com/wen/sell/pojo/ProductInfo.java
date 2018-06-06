package com.wen.sell.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wen.sell.enums.ProductStatusEnum;
import com.wen.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus = ProductStatusEnum.UP.getCode();

    private Integer categoryType;

    private String createdTime;

    private String updatedTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum () {
        return EnumUtil.getEnum(productStatus, ProductStatusEnum.class);
    }

    public ProductInfo() {
    }
}
