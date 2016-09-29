package com.cmp.test.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("BackupRankDay0106Job")
public class CountDownLatchTest implements Runnable {
    private static final Log logger = LogFactory.getLog(CountDownLatchTest.class);
    private static final int THREDS_SIZE = 20;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREDS_SIZE / 4);


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
        final CountDownLatch countDownLatch = new CountDownLatch(THREDS_SIZE);
        for (int i = 0; i < THREDS_SIZE; i++) {
            threadPool.submit(new CountDownLatchTest(countDownLatch));
        }
        logger.info("开始执行多线程移库操作");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
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

