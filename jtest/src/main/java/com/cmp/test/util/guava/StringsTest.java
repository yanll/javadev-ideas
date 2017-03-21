package com.cmp.test.util.guava;

import com.google.common.base.Strings;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by YAN on 2015/10/09.
 */
@Ignore
public class StringsTest {

    @Test
    public void test() {
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);

        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c, d);
        System.out.println("c,d common suffix is " + ourSuffix);


        int minLength = 8;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);

        String padStartResult = Strings.padStart("123", minLength, '0');
        System.out.println("padStartResult is " + padStartResult);


    }
}
