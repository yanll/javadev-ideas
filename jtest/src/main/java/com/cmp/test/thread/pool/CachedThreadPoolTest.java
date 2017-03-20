package com.cmp.test.thread.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YANLL on 2017/03/17.
 */
public class CachedThreadPoolTest {


    private static final int size = 100;


    final CountDownLatch latch = new CountDownLatch(100);

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Task t = new Task(0);
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.submit(t);
        }
    }
}


class Task implements Runnable {
    private int num;

    public Task() {

    }

    public Task(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            ++this.num;
            if (num % 10000 == 0)
                System.out.println(this.num);
        }
    }
}