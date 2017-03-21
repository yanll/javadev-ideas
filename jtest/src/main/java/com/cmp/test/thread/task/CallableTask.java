package com.cmp.test.thread.task;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by YANLL on 2017/03/20.
 */
public class CallableTask implements Callable<Integer> {
    private Integer num;

    public CallableTask() {
    }

    public CallableTask(Integer num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        while (this.num < 100) {
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.num++;
        }
        return this.num;
    }
}