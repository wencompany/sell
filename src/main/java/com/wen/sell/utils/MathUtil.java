package com.wen.sell.utils;

public class MathUtil {

    public static Boolean isEquals(Double d1, Double d2) {

        double result = Math.abs(d1 - d2);

        return result < 0.01 ? true : false;
    }
}
