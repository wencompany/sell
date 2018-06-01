package com.wen.sell.utils;

import com.wen.sell.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getEnum(Integer code, Class<T> enumClass) {

        for (T ever : enumClass.getEnumConstants()) {

            if (code.equals(ever.getCode())) {
                return ever;
            }
        }

        return null;
    }
}
