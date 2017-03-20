package com.cmp.test.designpattern.listener;

/**
 * Created by breez on 2016/01/05.
 */
public class Event extends java.util.EventObject {
    public Event(Object source) {
        super(source);
    }

    public void say() {
        System.out.println("This is say method...");
    }
}
