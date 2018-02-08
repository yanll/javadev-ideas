package com.cmp.test.thread;

import com.cmp.test.thread.task.CountDownLatchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchTest {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        CountDownLatchTask task = new CountDownLatchTask(countDownLatch);
        logger.info("开始执行多线程移库");
        for (int i = 0; i < 20; i++) {
            threadPool.submit(task);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        logger.info("多线程移库执行结束");
    }


}


