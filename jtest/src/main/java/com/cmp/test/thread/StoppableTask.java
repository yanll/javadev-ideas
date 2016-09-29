package com.cmp.test.thread;

/**
 * Created by breez on 2016/01/13.
 */
public class StoppableTask extends Thread {

    private volatile boolean pleaseStop;


    public void run() {

        while (!pleaseStop) {

            // do some stuff...

        }

    }


    public void tellMeToStop() {

        pleaseStop = true;

    }

}
