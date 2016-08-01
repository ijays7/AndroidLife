package com.ijays.androidlife.utils;

import java.text.SimpleDateFormat;

/**
 * Created by ijaysdev on 16/8/1.
 */

public class DateUtils {

    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }
}
