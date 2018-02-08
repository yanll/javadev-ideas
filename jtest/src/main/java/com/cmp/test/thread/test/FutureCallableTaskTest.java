package com.cmp.test.thread.test;

import com.cmp.test.thread.task.CalcResult;
import com.cmp.test.thread.task.CallableTask;
import com.cmp.test.thread.task.ThreadConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by YANLL on 2018/02/08.
 */
public class FutureCallableTaskTest {


    //todo-yll-fixme FutureTask RunnableFuture
    private static final Logger logger = LoggerFactory.getLogger(CallableTaskTest.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(ThreadConst.THREAD_POOL_SIZE);


    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始执行任务...");
        CallableTask task = new CallableTask(new Integer[]{1, 2, 3, 4, 5});
        CalcResult rs = task.call();
        List<Future<CalcResult>> list = new ArrayList<Future<CalcResult>>();
        for (int i = 0; i < 10; i++) {
            Future<CalcResult> future = executor.submit(task);
            list.add(future);
        }

        executor.shutdown();

        Integer total = 0;
        for (Future<CalcResult> future : list) {
            try {
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        System.out.println("Future:" + future + ",Result:" + future.get().getValue());
                        total += future.get().getValue();
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        System.out.println("所有任务执行完毕:" + total);

    }

}
