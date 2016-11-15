package com.yanll.framework.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-4-8
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);


    public static void copyFile(String src, String dest) throws IOException {
        FileInputStream in = new FileInputStream(src);
        inputStreamToFile(in, dest);
    }

    public static void inputStreamToFile(InputStream inputStream, String dest) throws IOException {
        File file = new File(dest);
        if (!file.exists())
            file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int c;
        byte buffer[] = new byte[1024];
        while ((c = inputStream.read(buffer)) != -1) {
            for (int i = 0; i < c; i++)
                out.write(buffer[i]);
        }
        inputStream.close();
        out.close();
    }

    public static String inputStreamToString(InputStream in) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines.toString();
        /**
         StringBuffer out = new StringBuffer();
         byte[] b = new byte[1024];
         for (int n; (n = in.read(b)) != -1; ) {
         out.append(new String(b, 0, n));
         }
         in.close();
         return out.toString();
         */
    }

    public static String bytesToStirng(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            logger.error("bytesToStirng error.", ex);
        } catch (IOException ex) {
            logger.error("bytesToStirng error.", ex);
        }
        return null;
    }


    public static void log(String file_name, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(file_name, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            logger.error("log error,", e);
        }
    }

    /**
     * 创建多级目录
     *
     * @param dir
     */
    public static boolean mkdirs(String dir) {
        File file = new File(dir);
        if (file.exists() && file.isDirectory())
            return true;
        return file.mkdirs();
    }


}
