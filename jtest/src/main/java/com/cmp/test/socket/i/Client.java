package com.cmp.test.socket.i;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Client implements Runnable {
    private String host = "localhost";
    private int port = 8189;

    public void chat() {
        try {
            Socket socket = new Socket(host, port);
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while (true) {
                    String accpet = in.readUTF();
                    System.out.println(accpet);
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        chat();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Client c = new Client();
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }
    }
}

