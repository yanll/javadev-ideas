package com.cmp.common.db;

/**
 * Created by YAN on 2015/12/13.
 */
public class HandleDataSource {


    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    public static String getDataSource() {
        return holder.get();
    }
}