package com.cmp.test.thread;

/**
 * Created by YAN on 2015/08/09.
 */
public class FishMeThread extends Thread {

    public static int s = 9;
    public volatile static String str = "";
    int count = 1, number;

    public FishMeThread(int num) {
        number = num;
        System.out.println("创建线程 " + number);
    }

    public void run() {
        while (true) {
            System.out.println("线程 " + number + ":计数 " + count);
            if (++count == 6) return;
        }
    }

    public static void main(String args[]) {
        for (int i = 0; i < 5; i++) {
            new FishMeThread(i + 1).start();
        }
    }

}
