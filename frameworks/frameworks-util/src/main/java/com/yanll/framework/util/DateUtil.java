package com.yanll.framework.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-3-19
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 * <p>
 * 时间
 */
public class DateUtil {

    public static class DateFormater {
        public static final String yyyy_MM_dd = "yyyy-MM-dd";
        public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
        public static final String yyyyMMdd = "yyyyMMdd";
        public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    }

    public static String currentDate() throws RuntimeException {
        return simpleDateFormater(DateFormater.yyyy_MM_dd).format(new Date());
    }

    public static String currentDateTime() throws RuntimeException {
        return simpleDateFormater(DateFormater.yyyy_MM_dd_HH_mm_ss).format(new Date());
    }

    public static String current(String parttern) throws RuntimeException {
        return simpleDateFormater(parttern).format(new Date());
    }

    public static String format(Date date, String parttern) throws RuntimeException {
        return simpleDateFormater(parttern).format(date);
    }

    private static SimpleDateFormat simpleDateFormater(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }


}
