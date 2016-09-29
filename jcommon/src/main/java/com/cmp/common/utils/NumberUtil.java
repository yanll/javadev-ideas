package com.cmp.common.utils;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-3-12
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class NumberUtil {

    public static boolean isNumber(Object value) {
        if (value instanceof Short || value instanceof Long || value instanceof Double || value instanceof Float || value instanceof Integer || value instanceof Byte)
            return true;
        return false;
    }

}


