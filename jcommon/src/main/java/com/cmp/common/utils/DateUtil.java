package com.cmp.common.utils;


import com.cmp.common.constants.Consts;

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


    public static String currentDate() throws RuntimeException {
        return simpleDateFormater(Consts.Formater.yyyy_MM_dd).format(new Date());
    }

    public static String currentDateTime() throws RuntimeException {
        return simpleDateFormater(Consts.Formater.yyyy_MM_dd_HH_mm_ss).format(new Date());
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
