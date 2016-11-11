package com.yanll.framework.util;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-4
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 * <p>
 * 加密解密
 */
public class CipherUtil {

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    public static String encryptPassword(String inputString) {
        return encodeByMD5(inputString);
    }


    public static boolean validatePassword(String password, String inputString) {
        if (password.equals(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }

    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }


    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        String pwd = args[0];
        String encryptPwd = "";
        CipherUtil cipher = new CipherUtil();
        System.out.println("pwd:" + pwd);
        encryptPwd = cipher.encryptPassword(pwd);
        System.out.println("encrypt_pwd:" + encryptPwd);

    }

}
