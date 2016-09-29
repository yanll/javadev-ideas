package com.cmp.test.sdk;

import org.junit.Ignore;

/**
 * Created by YLL on 2015/07/28.
 */
@Ignore
public class Test {


    @org.junit.Test
    public void test() {
        Object o1 = new Object();
        Object o2 = new Object();

        System.out.println(o1.equals(o2));
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        short s = -32768;
        byte b = 127;
        char c = 'C';
        int rs = 2 << 3;
        System.out.println(rs);
        try {
            System.out.println("1");
            return;
        } finally {
            System.out.println("3");
        }


    }


}
