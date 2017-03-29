package com.cmp.test.calc;

import java.util.Random;

/**
 * Created by YANLL on 2017/03/22.
 */
public class QuicksortTest {
    private static final int len = 50;

    public static void main(String[] args) {
        QuicksortTest quicksort = new QuicksortTest();
        //quicksort.sortI();
        //System.out.println("斐波那契数列：" + quicksort.sortII(15));
        //System.out.println("阶乘：" + quicksort.sortIII(6));
        //quicksort.sortIIII();
        quicksort.cal();
    }

    private void sortI() {
        int[] arr = initArr();
        int tmp;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        System.out.println("排序后数组：");
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private int sortII(int n) {
        //1 1 2 3 5 8 13 21 34 55 89 144 233 377 610
        if (n <= 2) return 1;
        int rs1 = sortII(n - 2);
        int rs2 = sortII(n - 1);
        return rs1 + rs2;
    }

    private int sortIII(int n) {
        //1*2*3*4*5*6
        if (n <= 1) return 1;
        return sortIII(n - 1) * n;
    }

    private void sortIIII() {
        int[] arr = initArr();
        int base = arr[0];
        for (int i = 0; i < len; i++) {

        }
    }


    private int[] initArr() {
        int[] arr = new int[len];
        Random random = new Random();
        System.out.println("初始化数组：");
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(200);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        return arr;
    }

    private void cal() {
        int num = 2899;
        System.out.println("二进制：" + Integer.toBinaryString(num));
        num = num >> -1;
        System.out.println("二进制：" + Integer.toBinaryString(num));
        System.out.println("二进制：" + Integer.toBinaryString(15));
        System.out.println(num);
    }
}
