package com.cmp.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
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


class CountDownLatchTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTask.class);
    private CountDownLatch latch;

    public CountDownLatchTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        boolean res = true;
        try {
            try {
                logger.info(Thread.currentThread().getName() + " working...");
                Thread.sleep(new Random().nextInt(1000));
            } catch (Exception e) {
                logger.error("迁移数据异常", e);
            }
        } finally {
            latch.countDown();
        }
    }
}

