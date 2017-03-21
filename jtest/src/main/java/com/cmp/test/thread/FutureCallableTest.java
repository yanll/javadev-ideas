package com.cmp.test.thread;

import com.cmp.test.thread.task.CallableTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by YANLL on 2017/03/21.
 */
public class FutureCallableTest {
    private static final ExecutorService executor = Executors.newFixedThreadPool(ThreadConst.THREAD_POOL_SIZE);

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始执行任务");
        List<Future<Integer>> list = new ArrayList<>();
        CallableTask task = new CallableTask(0);
        Integer sum = 0;
        for (int i = 0; i < 20; i++) {
            executor.submit(task);
            Future<Integer> future = executor.submit(task);
            list.add(future);
        }

        executor.submit(new Runnable() {
            @Override
            public void run() {
                for (Future<Integer> future : list) {
                    try {
                        while (true) {
                            if (future.isDone() && !future.isCancelled()) {
                                System.out.println("Future:" + future + ",Result:" + future.get());
                                break;
                            } else {
                                Thread.sleep(1000);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executor.shutdown();
        System.out.println("所有任务执行完毕:" + sum);
    }
}
