package com.cmp.test.designpattern.listener;

/**
 * Created by breez on 2016/01/05.
 */
public interface DemoListener extends java.util.EventListener {
    public void handleEvent(DemoEvent dm);
}
