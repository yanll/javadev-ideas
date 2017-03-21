package com.cmp.test.thread.pool;

import com.cmp.test.thread.ThreadConst;
import com.cmp.test.thread.task.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YANLL on 2017/03/20.
 */
public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(ThreadConst.THREAD_POOL_SIZE);
        RunnableTask task = new RunnableTask(0);
        for (int i = 0; i < 20; i++) {
            fixedThreadPool.submit(task);
        }
    }
}
