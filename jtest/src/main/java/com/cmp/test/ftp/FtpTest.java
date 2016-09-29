package com.cmp.test.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by breez on 2015/12/21.
 */
public class FtpTest {
    public static void main(String[] args) {
        testDownload();
    }

    /**
     * FTP下载单个文件测试
     */
    public static void testDownload() {


        FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect("v0.api.upyun.com");
            ftpClient.login("imgftp/img-hotyq", "dtXDxd65GpQDrnds");


            ftpClient.setBufferSize(1024);


            FTPFile[] ss = ftpClient.listFiles();

            System.out.println(ss.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }
}
