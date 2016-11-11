package com.yanll.framework.util;

/**
 * 跟踪工具类
 *
 * @author HongjianZ
 */
public class UTrace {
    static final String PACKAGE_ROOT = "";
    static final String SEPARATOR = "\n";
    static final String CAUSED_BY = "\nCaused by:";

    public static String trace(Throwable e, Object... args) {
        return trace(PACKAGE_ROOT, e, args);
    }

    public static String causedBy(Throwable e) {
        StringBuilder sb = new StringBuilder();
        causedBy(sb, e);
        return sb.toString();
    }

    static void causedBy(StringBuilder sb, Throwable e) {
        sb.append(SEPARATOR);
        do {
            sb.append(e);
            sb.append(CAUSED_BY);
            sb.append(SEPARATOR);
        } while ((e = e.getCause()) != null);
    }

    public static String trace(String packageName, Throwable e, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(trace(packageName, e.getStackTrace()));
        causedBy(sb, e);
        return sb.toString();
    }

    public static String trace(String packageName, StackTraceElement[] elements) {
        return trace(packageName, elements, 0, elements.length);
    }

    public static String trace(String packageName,
                               StackTraceElement[] elements, int a, int b) {
        StringBuilder sb = new StringBuilder();
        int i = Math.max(Math.min(a, b), 0);
        int j = Math.min(Math.max(a, b), elements.length - 1);
        while (i < j) {
            String s = trace(elements[i]);
            if (s.contains(packageName)) {
                sb.append(SEPARATOR);
                sb.append(s);
            }
            i++;
        }
        return sb.toString();
    }

    public static String trace(StackTraceElement e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getClassName());
        sb.append('.');
        sb.append(e.getMethodName());
        sb.append('(');
        sb.append(e.getFileName());
        sb.append(':');
        sb.append(e.getLineNumber());
        sb.append(')');
        return sb.toString();
    }
}
