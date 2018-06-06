package com.wen.sell.form;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductCategoryForm {
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;
}
