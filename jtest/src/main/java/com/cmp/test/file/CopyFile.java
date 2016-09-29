package com.cmp.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by YANLL on 2016/07/17.
 */
public class CopyFile implements Runnable {
    //来源文件
    private String sourceFileName;
    //目标文件
    private String targetFileName;
    //分块总数
    private int blockCount;
    //开始COPY的块序号
    private int blockNo;
    //缓存大小
    private int maxBuffSize = 1024 * 1024;

    /**
     * 将sourceFileName文件分blockCount块后的第blockNo块复制至sourceFileName
     *
     * @param sourceFileName 来源文件全名
     * @param targetFileName 目标文件全名
     * @param blockCount     文件分块COPY数
     * @param blockNo        开始COPY的块序号
     */
    public CopyFile(String sourceFileName, String targetFileName, int blockCount, int blockNo) {
        this.sourceFileName = sourceFileName;
        this.targetFileName = targetFileName;
        this.blockCount = blockCount;
        this.blockNo = blockNo;
    }

    public void run() {
        //得到来源文件
        File file = new File(sourceFileName);
        //得到来源文件的大小
        long size = file.length();
        //根据文件大小及分块总数算出单个块的大小
        long blockLenth = size / blockCount;
        //算出当前开始COPY的位置
        long startPosition = blockLenth * blockNo;
        //实例化缓存
        byte[] buff = new byte[maxBuffSize];
        try {
            //从源文件得到输入流
            InputStream inputStream = new FileInputStream(sourceFileName);
            //得到目标文件的随机访问对象
            RandomAccessFile raf = new RandomAccessFile(targetFileName, "rw");
            //将目标文件的指针偏移至开始位置
            raf.seek(startPosition);
            //当前读取的字节数
            int curRedLength;
            //累计读取字节数的和
            int totalRedLength = 0;
            //将来源文件的指针偏移至开始位置
            inputStream.skip(startPosition);
            //依次分块读取文件
            while ((curRedLength = inputStream.read(buff)) > 0 && totalRedLength < blockLenth) {
                //将缓存中的字节写入文件?目标文件中
                raf.write(buff, 0, curRedLength);
                if (blockNo == 0) {
                    System.out.println(Thread.currentThread().getName());
                }
                //累计读取的字节数
                totalRedLength += curRedLength;
            }
            //关闭相关资源
            raf.close();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFile = "F:\\电影\\极盗者\\blow-point.break.2015.720p.bluray.x264.mkv";
        String targetFile = "F:\\电影\\极盗者\\i.mkv";


        int blockCount = 1000;
        //记录开始时间
        long beginTime = System.currentTimeMillis();
        //依次分块进行文件COPY
        for (int i = 0; i < blockCount; i++) {
            //实例化文件复制对象
            CopyFile copyFile = new CopyFile(sourceFile, targetFile, blockCount, i);
            //实例化线程
            Thread thread = new Thread(copyFile);
            //开始线程
            thread.start();
            try {
                //加入线程
                thread.join();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        //计算耗时
        long endTime = System.currentTimeMillis();
        //输出耗时
        //System.out.println("共用时:" + (endTime - beginTime) + "ms");

    }


}

