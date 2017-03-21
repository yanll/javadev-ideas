package com.cmp.test.designpattern.listener;

/**
 * Created by breez on 2016/01/05.
 */
public class ListenerImpl implements IListener {
    public void handleEvent(Event de) {
        System.out.println("Inside listener...");
        de.say();
    }
}