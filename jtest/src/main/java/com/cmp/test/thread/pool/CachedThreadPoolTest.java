package com.cmp.test.thread.pool;

import com.cmp.test.thread.task.RunnableTask;

import java.util.concurrent.*;

/**
 * Created by YANLL on 2017/03/17.
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        RunnableTask task = new RunnableTask(0);
        for (int i = 0; i < 20; i++) {
            cachedThreadPool.submit(task);
        }
    }
}






