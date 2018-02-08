package com.cmp.test.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by YANLL on 2017/03/20.
 */
public class CallableTask implements Callable<CalcResult> {

    private static final Logger logger = LoggerFactory.getLogger(CallableTask.class);

    private Integer[] values;

    public CallableTask() {
    }

    public CallableTask(Integer[] values) {
        this.values = values;
    }

    @Override
    public CalcResult call() throws Exception {
        CalcResult result = new CalcResult();
        Integer total = 0;
        for (Integer v : values) {
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                logger.error("", e);
            }
            total += v;
        }
        result.setValue(total);
        return result;
    }
}