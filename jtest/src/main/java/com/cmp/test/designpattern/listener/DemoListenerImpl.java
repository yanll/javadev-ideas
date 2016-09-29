package com.cmp.test.designpattern.listener;

/**
 * Created by breez on 2016/01/05.
 */
public class DemoListenerImpl implements DemoListener {
    public void handleEvent(DemoEvent de) {
        System.out.println("Inside listener...");
        de.say();
    }
}