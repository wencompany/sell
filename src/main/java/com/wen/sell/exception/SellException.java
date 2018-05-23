package com.wen.sell.exception;

import com.wen.sell.enums.ResultEnum;

public class SellException extends RuntimeException {

    private Integer code;

    private  String message;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
