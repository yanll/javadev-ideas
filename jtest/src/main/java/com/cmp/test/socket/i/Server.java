package com.cmp.test.socket.i;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Server implements Runnable {
    private int port = 8189;
    private ServerSocket server = null;
    private Socket socket = null;

    public Server() throws IOException {
        server = new ServerSocket(port);
    }


    public void service() {
        try {
            Socket socket = server.accept();
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String send = scanner.nextLine();
                    out.writeUTF("server:" + send);
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
        service();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Server s = new Server();
            Future f = pool.submit(s);
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
