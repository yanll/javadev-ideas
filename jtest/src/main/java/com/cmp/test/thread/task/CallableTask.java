package com.cmp.test.thread.task;

import java.util.concurrent.Callable;

/**
 * Created by YANLL on 2017/03/20.
 */
public class CallableTask implements Callable {
    private int num;

    public CallableTask() {

    }

    public CallableTask(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}