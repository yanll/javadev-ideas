package com.yanll.framework.util;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-3-29
 * Time: 上午12:21
 * To change this template use File | Settings | File Templates.
 */
public class HttpUtil {

    /**
     * @param post_url 目标URL
     * @param params   请求参数
     * @param callback 回调函数
     * @throws java.io.IOException
     */
    public static void post(String post_url, InputStream params, CallBack callback) throws IOException {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(post_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/html");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setUseCaches(false);
            connection.connect();
            connection.setConnectTimeout(15000);
            DataInputStream in = new DataInputStream(params);
            OutputStream out = connection.getOutputStream();
            int bytes = 0;
            byte[] buffer = new byte[1024];
            while ((bytes = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytes);
            }
            in.close();
            out.flush();
            out.close();
            InputStream inputStream = connection.getInputStream();
            callback.print(inputStream);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * @param get_url  目标URL
     * @param callback 回调函数
     * @throws java.io.IOException
     */
    public static void get(String get_url, CallBack callback) throws IOException {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(get_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            callback.print(inputStream);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    public interface CallBack {
        public void print(InputStream is) throws IOException;
    }
}
