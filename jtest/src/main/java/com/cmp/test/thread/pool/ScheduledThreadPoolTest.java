package com.cmp.test.thread.pool;

import com.cmp.test.thread.task.ThreadConst;
import com.cmp.test.thread.task.RunnableTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by YANLL on 2017/03/20.
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(ThreadConst.THREAD_POOL_SIZE);
        scheduledThreadPool.schedule(new RunnableTask(), 5, TimeUnit.SECONDS);//延迟5秒后仅执行一次
        scheduledThreadPool.scheduleAtFixedRate(new RunnableTask(), 0, 10L, TimeUnit.SECONDS);//固定每10秒执行一次
        scheduledThreadPool.scheduleWithFixedDelay(new RunnableTask(), 0, 10L, TimeUnit.SECONDS);//每次上一任务完成后隔10秒执行一次
    }
}
