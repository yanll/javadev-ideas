package com.cmp.test.sdk.enums;

import java.util.Random;

/**
 * Created by breez on 2016/01/22.
 */
public enum Input {
    /**五分硬币**/
    NICKEL(5),
    /**一角硬币**/
    DIME(10),
    /**两角五分**/
    QUARTER(25),
    /**一块美金**/
    DOLLAR(100),

    /**药膏2元**/
    TOOTHPASTE(200),
    /**炸薯条75美分**/
    CHIPS(75),
    /**苏打水1元**/
    SODA(100),//
    /**肥皂5毛**/
    SOAP(50),

    ABORT_TRANSACTION{
        public int amount(){
            throw new RuntimeException("退出时不能获取余额！");
        }
    },
    STOP{
        public int amount(){
            throw new RuntimeException("关机时不能获取余额！");
        }
    };

    //金额
    int value;
    Input(int value){this.value = value;}
    Input(){}
    //返回该操作项的金额
    int amount(){return value;}

    /**
     * @return 随机获取的操作
     */
    public static Input randomSelection(){
        return values()[new Random(System.nanoTime()).nextInt(values().length)];
    }
}
