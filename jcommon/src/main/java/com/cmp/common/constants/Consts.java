package com.cmp.common.constants;


import com.cmp.common.utils.USystem;

import java.util.HashMap;
import java.util.Map;

public class Consts {

    public static final String UTF_8 = "UTF-8";
    public static final String WEB_ROOT_KEY = "webapp.root";
    public static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    public static final String OS_NAME = USystem.getOs().name();
    public static final boolean IS_WINDOWS = OS_NAME.toUpperCase().contains("WINDOWS");
    public static final boolean IS_LINUX = OS_NAME.toUpperCase().contains("LINUX");
    private static final String ROOT_CLASSPATH_ = IS_WINDOWS ? Consts.class.getResource("/").getPath().substring(1) : Consts.class.getResource("/").getPath().substring(5);
    private static final Integer IS_FILE_PREFIX = ROOT_CLASSPATH_.indexOf("file:");
    private static final String ROOT_CLASSPATH = IS_FILE_PREFIX == 0 ? ROOT_CLASSPATH_.substring(5) : ROOT_CLASSPATH_;// 去掉前缀"file:"
    private static final Integer WEB_INF_INDEX = ROOT_CLASSPATH.indexOf("/WEB-INF");
    public static final String WEB_ROOT = WEB_INF_INDEX < 0 ? System.getProperty("webapp.root") : ROOT_CLASSPATH.substring(0, WEB_INF_INDEX);


    public static class YESNO {
        public static final Integer Y = 1;
        public static final Integer N = 0;
    }

    public static class Formater {
        public static final String yyyy_MM_dd = "yyyy-MM-dd";
        public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
        public static final String yyyyMMdd = "yyyyMMdd";
        public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    }

    public static Map<Integer, String> ERR_CODE = new HashMap<>();

    static {
        ERR_CODE.put(0, "请求超时，过一会儿再来试下吧！");
        ERR_CODE.put(1000, "操作成功！");
    }


    public static void main(String[] args) {

    }
}
