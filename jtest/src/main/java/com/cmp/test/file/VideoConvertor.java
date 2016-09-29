//package com.cmp.test.file;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.io.*;
//import java.util.List;
//
///**
// * Created by YANLL on 2016/08/02.
// */
//public class VideoConvertor {
//
//    private static final Log logger = LogFactory.getLog(VideoConvertor.class);
//
//
//
//
//    /**
//     * 根据水印图片---添加视频水印
//     *
//     * @param srcPath       原视频路径
//     * @param tarVideoPath  生成后的视频路径
//     * @param waterMarkPath 水印图片路径
//     * @param wmPosition    水印位子
//     * @param alpha         透明度
//     * @return
//     */
//    public static boolean processFfmpegWatermarkByImg(String srcPath, String tarVideoPath, String waterMarkPath, int wmPosition, float alpha) {
//        if (!checkfile(srcPath)) {
//            logger.error("【" + srcPath + "】  不存在 !");
//            return false;
//        }
//
//        if (!checkfile(waterMarkPath)) {
//            logger.error("【" + waterMarkPath + "】  不存在 !");
//            return false;
//        }
//        //如果父目录不存在就创建一个
//        tarVideoPath = BaseCommonUtil.replaceFliePathStr(tarVideoPath);
//        BaseCommonUtil.mkdir(tarVideoPath);
//        //要执行的shell脚本路径
//
//        String shellPath = WebappConfigUtil.getParameter("shellPath");
//        if (!checkfile(shellPath)) {
//            logger.error("【" + shellPath + "】shell脚本路径 不存在 !");
//        }
//        String extendTarName = tarVideoPath.substring(tarVideoPath.lastIndexOf(".") + 1, tarVideoPath.length());
//        Process process = null;
//        try {
//            String os = System.getProperty("os.name");
//            if (os != null && os.toLowerCase().startsWith("windows")) {
//                String picPath = waterMarkPath.substring(waterMarkPath.indexOf("WebRoot"), waterMarkPath.lastIndexOf("/"));
//                waterMarkPath = waterMarkPath.substring(waterMarkPath.lastIndexOf("/") + 1, waterMarkPath.length());
//                process = Runtime.getRuntime().exec(shellPath + " " + picPath + " " + ffmpegPath + " " + srcPath + " " + waterMarkPath + " " + getVideoPosition(wmPosition) + " " + tarVideoPath);
//            } else {
//                process = Runtime.getRuntime().exec(shellPath + " " + ffmpegPath + " " + srcPath + " " + waterMarkPath + " " + getVideoPosition(wmPosition) + " " + tarVideoPath);
//            }
//            doWaitFor(process);
//            //转换mate信息
//            if ("MP4".equals(extendTarName.toUpperCase())) {
//                return execMp4Box(tarVideoPath);
//            }
//
//            if (!checkfile(tarVideoPath)) {
//                logger.error("【" + srcPath + "】processFfmpegWatermarkByImage  视频添加水印不成功 !");
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("【" + srcPath + "】processFfmpegWatermarkByImage  视频添加水印不成功 !");
//            return false;
//        } finally {
//            if (process != null) {
//                process.destroy();
//            }
//        }
//    }
//
//
//    /**
//     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
//     *
//     * @param filePath
//     * @return
//     */
//    private static String processVideo(String filePath) {
//        List<String> commend = new java.util.ArrayList<String>();
//        commend.add(ffmpegPath);//可以设置环境变量从而省去这行
//        commend.add("-i");
//        commend.add(filePath);
//        try {
//            ProcessBuilder builder = new ProcessBuilder();
//            builder.command(commend);
//            builder.redirectErrorStream(true);
//            Process p = builder.start();
//            BufferedReader buf = null; // 保存ffmpeg的输出结果流
//            String line = null;
//            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            while ((line = buf.readLine()) != null) {
//                sb.append(line);
//                continue;
//            }
//            p.waitFor();//这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
//            return sb.toString();
//        } catch (Exception e) {
//            logger.error("ffmpeg解析视频文件【" + filePath + "】失败!");
//            return null;
//        }
//    }
//
//    /**
//     * 判断文件是否存在
//     *
//     * @param path
//     * @return
//     */
//    public static boolean checkfile(String path) {
//        File file = new File(path);
//        if (!file.isFile()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//
//}
