package com.mason.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: Mason
 * Date: 2018/7/12
 * Time: 14:55
 */
public class DateUtil {
    public static String getCurrentDate(){
        //当前时间
        Date now = new Date();
        Date newDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =sdf.format(now);
        return dateStr;
    }
}
