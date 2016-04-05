package com.wa.net.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 16-4-1.
 **/
public class StringUtil {

    public static String  getStringDate(Date date) {
        SimpleDateFormat formatter = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
        return  formatter.format(date);
    }

}
