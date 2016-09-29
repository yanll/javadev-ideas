package com.cmp.test.sdk;

/**
 * Created by YAN on 2015/08/28.
 */
public class SystemTest {

    public static void main(String[] args) {

        System.setProperty("k", "V");
        System.out.println(System.getProperty("k"));

    }

}
