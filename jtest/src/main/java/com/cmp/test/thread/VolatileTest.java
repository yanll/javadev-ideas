package com.cmp.test.thread;

import com.cmp.test.thread.task.ThreadConst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by breez on 2016/01/13.
 */
public class VolatileTest {


    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ThreadConst.THREAD_POOL_SIZE);
        Volatile task = new Volatile(new LongAdder(), 0);
        for (int i = 0; i < 1000; i++) {
            if (i == 50) task.stop();
            fixedThreadPool.submit(task);
        }
    }

}

class Volatile implements Runnable {
    private LongAdder la = new LongAdder();
    private int i;
    private boolean stop;

    public Volatile(LongAdder la, int i) {
        this.la = la;
        this.i = i;
    }

    @Override
    public void run() {
        while (i < 10000) {
            //la.increment();
            /*if (la.longValue() % 100 == 0) System.out.println(la.longValue());*/
            i++;
        }
        System.out.println(i);
    }

    public void stop() {
        stop = true;
    }
}