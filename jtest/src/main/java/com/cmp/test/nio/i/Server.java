package com.cmp.test.nio.i;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    private int port = 8189;
    private ServerSocket server = null;

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


    public static void main(String[] args) throws IOException {
        new Server().service();
    }

}
