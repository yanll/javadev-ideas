package com.cmp.test.designpattern.listener;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by breez on 2016/01/05.
 */
public class DemoSource {
    private Vector<DemoListener> repository = new Vector<>();

    public DemoSource() {
    }

    public void addDemoListener(DemoListener d) {
        repository.addElement(d);
    }

    public void notifyDemoEvent() {//通知所有的监听器
        Enumeration<DemoListener> e = repository.elements();
        while (e.hasMoreElements()) {
            DemoListener d = e.nextElement();
            d.handleEvent(new DemoEvent(this));
        }
    }
}
