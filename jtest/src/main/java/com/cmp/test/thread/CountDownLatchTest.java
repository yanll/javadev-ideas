package com.cmp.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchTest implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);


    private CountDownLatch latch;

    public CountDownLatchTest() {
    }

    public CountDownLatchTest(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 创建10个线程执行移库操作
     */
    public void moveRankViewDataJob() {
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            threadPool.submit(new CountDownLatchTest(countDownLatch));
        }
        logger.info("开始执行多线程移库");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        logger.info("多线程移库执行结束");
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


    public static void main(String[] args) {
        new CountDownLatchTest().moveRankViewDataJob();
    }


}

