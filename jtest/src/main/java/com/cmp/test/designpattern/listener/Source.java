package com.cmp.test.designpattern.listener;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by breez on 2016/01/05.
 */
public class Source {
    private Vector<IListener> repository = new Vector<>();

    public Source() {
    }

    public void addDemoListener(IListener d) {
        repository.addElement(d);
    }

    public void notifyDemoEvent() {//通知所有的监听器
        Enumeration<IListener> e = repository.elements();
        while (e.hasMoreElements()) {
            IListener d = e.nextElement();
            d.handleEvent(new Event(this));
        }
    }
}
