package com.cmp.test.thread.pool;

import com.cmp.test.thread.task.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YANLL on 2017/03/20.
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        RunnableTask task = new RunnableTask(0);
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.execute(task);
        }
    }
}
