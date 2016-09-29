package com.cmp.test.sdk.enums;

import java.util.EnumSet;

/**
 * Created by YLL on 2015/08/06.
 */
public class Main {
    public static void main(String[] args) {


        IKeys.Cache cache = IKeys.Cache.IndexData;

        System.out.println(cache.name());
        System.out.println(cache.toString());
        System.out.println(cache.getDeclaringClass().getSimpleName());
        System.out.println(cache.getClass().getName());
        System.out.println(Enum.valueOf(IKeys.Cache.class,"IndexData").getMemo());
        cache.doit();






        /*BastDictEnum.流程状态.get("cc");
        System.out.println(BastDictEnum.流程状态.name());*/

        /*System.out.println(JdbcType.DATE.TYPE_CODE);
        System.out.println(JdbcType.DATE.toString());
        System.out.println(JdbcType.DATE);
        System.out.println(JdbcType.forCode(1));
        System.out.println(JdbcType.valueOf(""));*/


        /*int a = 2;
        int b = 8;
        a = (a * b) / a;
        b = (a * b) / b;

        System.out.println("a:" + a + ",b:" + b);*/
    }
}
