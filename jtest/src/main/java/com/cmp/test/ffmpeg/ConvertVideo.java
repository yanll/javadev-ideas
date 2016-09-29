package com.cmp.test.ffmpeg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANLL on 0/0/04.
 */
public class ConvertVideo {

    static String exe = "D:\\env\\ffmpeg-win64-static\\bin\\ffmpeg";
    static String in = "D:\\workspace\\svnres\\projs\\z\\test.avi";
    static String out = "D:\\workspace\\svnres\\projs\\z\\test.mp4";


    public static void main(String args[]) throws IOException {
        process();
    }
    //ffmpeg -i input.avi -vf "movie=logo.png [logo]; [in][logo] overlay=10:10 [out]" output.avi
    //ffmpeg -ss 0:0:5 -t 0:0:20 -i test.avi -vcodec copy -acodec copy a.avi    //剪切视频
    private static boolean process() {
        List<String> command = new ArrayList<String>();
        command.add(exe);
        command.add("-i");
        command.add(in);
        command.add("-threads");
        command.add("10");
        command.add(out);
        command.add("-y");
        try {
            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
            new PrintStream(videoProcess.getErrorStream()).start();
            new PrintStream(videoProcess.getInputStream()).start();
            videoProcess.waitFor();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

class PrintStream extends Thread {
    java.io.InputStream __is = null;

    public PrintStream(java.io.InputStream is) {
        __is = is;
    }

    public void run() {
        try {
            while (this != null) {
                int _ch = __is.read();
                if (_ch != -1)
                    System.out.print((char) _ch);
                else break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}