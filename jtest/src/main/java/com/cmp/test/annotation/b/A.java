package com.cmp.test.annotation.b;

/**
 * Created by YAN on 2015/08/15.
 */
public class A {
    private static A ourInstance = new A();

    public static A getInstance() {
        return ourInstance;
    }

    private A() {
    }
}
