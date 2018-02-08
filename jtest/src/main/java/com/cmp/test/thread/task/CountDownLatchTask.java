package com.cmp.test.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by YANLL on 2018/02/08.
 */
public class CountDownLatchTask implements Runnable {

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
