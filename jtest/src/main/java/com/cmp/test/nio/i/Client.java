package com.cmp.test.nio.i;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {
    private String host = "localhost";
    private int port = 8189;

    public void chat() {
        String msg = null;
        try {
            Socket socket = new Socket(host, port);
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while (true) {
                    msg = in.readUTF();
                    System.out.println(msg);
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().chat();
    }
}

