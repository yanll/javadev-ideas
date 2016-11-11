package com.yanll.framework.util;

import java.util.Random;

public class RandomUtil {

    /**
     * 六位长度随机整数
     *
     * @return
     */
    public static int random(int... ii) {
        Random random = new Random();
        Integer i = new Integer(random.nextInt(900000) + 100000);
        return i;
    }

    public static void main(String[] args) {
        System.out.println(random());
    }
}
