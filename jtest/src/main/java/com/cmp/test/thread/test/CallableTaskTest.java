package com.cmp.test.thread.test;

import com.cmp.test.thread.task.CalcResult;
import com.cmp.test.thread.task.CallableTask;
import com.cmp.test.thread.task.ThreadConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by YANLL on 2018/02/08.
 */
public class CallableTaskTest {

    private static final Logger logger = LoggerFactory.getLogger(CallableTaskTest.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(ThreadConst.THREAD_POOL_SIZE);


    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始执行任务...");
        CallableTask task = new CallableTask(new Integer[]{1, 2, 3, 4, 5});
        Future<CalcResult> rs = executor.submit(task);
        long s = System.currentTimeMillis();
        while (true) {
            if (rs.isDone() && !rs.isCancelled()) {
                System.out.println("异步任务执行完毕:" + rs.get().getValue());
                break;
            } else {
                if (System.currentTimeMillis() - s > 2000) {
                    System.out.println("执行超时！");
                    rs.cancel(false);
                    break;
                }
            }
        }
    }

}
