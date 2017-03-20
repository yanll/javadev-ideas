package com.cmp.test.thread.task;

import java.util.Random;

/**
 * Created by YANLL on 2017/03/20.
 */
public class RunnableTask implements Runnable {
    private int num;

    public RunnableTask() {

    }


    public RunnableTask(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    private void test() {
        int num = 0;
        for (int i = 0; i < 10; i++) {
            num++;
        }
        System.out.println(num);
    }

    @Override
    public void run() {
        /*test();*/
        while (this.num < 100) {
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.num++;
        }
        if (num >= 100) System.out.println(this.num);
    }
}
