package com.yanll.framework.util.constants;


import com.yanll.framework.util.USystem;

public class Const {

    public static final String UTF_8 = "UTF-8";
    public static final String WEB_ROOT_KEY = "webapp.root";
    public static final String CONTENT_TYPE_TEXT = "text/html;charset=utf-8";
    public static final String OS_NAME = USystem.getOs().name();
    public static final boolean IS_WINDOWS = OS_NAME.toUpperCase().contains("WINDOWS");
    public static final boolean IS_LINUX = OS_NAME.toUpperCase().contains("LINUX");
    private static final String ROOT_CLASSPATH_ = IS_WINDOWS ? Const.class.getResource("/").getPath().substring(1) : Const.class.getResource("/").getPath().substring(5);
    private static final Integer IS_FILE_PREFIX = ROOT_CLASSPATH_.indexOf("file:");
    private static final String ROOT_CLASSPATH = IS_FILE_PREFIX == 0 ? ROOT_CLASSPATH_.substring(5) : ROOT_CLASSPATH_;// 去掉前缀"file:"
    private static final Integer WEB_INF_INDEX = ROOT_CLASSPATH.indexOf("/WEB-INF");
    public static final String WEB_ROOT = WEB_INF_INDEX < 0 ? System.getProperty("webapp.root") : ROOT_CLASSPATH.substring(0, WEB_INF_INDEX);

    public static class YESNO {
        public static final int Y = 1;
        public static final int N = 0;
    }

}
