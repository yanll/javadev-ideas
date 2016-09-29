package com.cmp.common.utils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-3-27
 * Time: 下午12:05
 * To change this template use File | Settings | File Templates.
 */
public class MapUtil {
    public static String getStringValue(Map<String, ?> map, String key) throws RuntimeException {
        if (map.get(key) == null) {
            return null;
        }
        return map.get(key).toString();
    }

    public static int getIntValue(Map<String, ?> map, String key) throws RuntimeException {
        if (map.get(key) == null) {
            return 0;
        }
        return Integer.parseInt(map.get(key).toString());
    }

    public static long getLongValue(Map<String, ?> map, String key) throws RuntimeException {
        if (map.get(key) == null) {
            return 0L;
        }
        return Long.parseLong(map.get(key).toString());
    }

    public static double getDoubleValue(Map<String, ?> map, String key) throws RuntimeException {
        if (map.get(key) == null) {
            return 0D;
        }
        return Double.parseDouble(map.get(key).toString());
    }
}
