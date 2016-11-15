package com.yanll.framework.util.enums;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
public class EnumUtil {
    private static final Logger logger = LoggerFactory.getLogger(EnumUtil.class);

    public static <T> boolean exists(T value, Class em) {
        try {
            Method m = em.getMethod("getValue");
            Object[] objs = em.getEnumConstants();
            for (Object obj : objs) {
                if (m.invoke(obj).equals(value)) return true;
            }
        } catch (NoSuchMethodException e) {
            logger.error("NoSuchMethodException.", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException.", e);
        } catch (Exception e) {
            logger.error("Exception.", e);
        }
        return false;
    }

    public static Map<Integer, String> toMap(Class em) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Method m = em.getMethod("getValue");
            Method m_ = em.getMethod("getDesc");
            Object[] objs = em.getEnumConstants();
            for (Object obj : objs) {
                Integer k = (Integer) m.invoke(obj);
                String v = (String) m_.invoke(obj);
                map.put(k, v);
            }
        } catch (NoSuchMethodException e) {
            logger.error("NoSuchMethodException.", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException.", e);
        } catch (Exception e) {
            logger.error("Exception.", e);
        }
        return map;
    }
}
