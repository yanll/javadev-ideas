package com.cmp.common.enums;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface IEnum {

    class Util {

        private static final Log logger = LogFactory.getLog(Util.class);

        public static <T> boolean exists(T value, Class em) {
            try {
                Method m = em.getMethod("getValue");
                Object[] objs = em.getEnumConstants();
                for (Object obj : objs) {
                    if (m.invoke(obj).equals(value)) return true;
                }
            } catch (NoSuchMethodException e) {
                logger.error("IEnum.Util.exists NoSuchMethodException.", e);
            } catch (InvocationTargetException e) {
                logger.error("IEnum.Util.exists InvocationTargetException.", e);
            } catch (Exception e) {
                logger.error("IEnum.Util.exists Exception.", e);
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
                logger.error("IEnum.Util.toMap NoSuchMethodException.", e);
            } catch (InvocationTargetException e) {
                logger.error("IEnum.Util.toMap InvocationTargetException.", e);
            } catch (Exception e) {
                logger.error("IEnum.Util.toMap Exception.", e);
            }
            return map;
        }
    }


    /**
     * ID和数据库表b_index_data_def对应
     * E***：易推
     */
    public enum IndexDefinition {
        INDEX_LOOP(1L, "轮播"),
        INDEX_NAVI(2L, "导航");
        private Long value;
        private String desc;

        private IndexDefinition(Long value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Long getValue() {
            return this.value;
        }

        public static IndexDefinition getInstance(Long value) {
            IndexDefinition instance = null;
            for (IndexDefinition v : IndexDefinition.values()) {
                if (v.value == value) {
                    return v;
                }
            }
            return null;
        }
    }

    public enum YESNO {
        Y(1, "是"), N(0, "否");
        private Integer value;
        private String desc;

        private YESNO(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return this.value;
        }

        public String getDesc() {
            return desc;
        }

    }


}
