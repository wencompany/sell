package com.wen.sell.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
    public static final String TIME_FORMATER = "yyyy-MM-dd HH:mm:ss";

    public static String nowTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMATER);
        return simpleDateFormat.format(new Date());
    }
}
