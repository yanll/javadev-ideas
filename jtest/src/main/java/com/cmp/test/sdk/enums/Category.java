package com.cmp.test.sdk.enums;

import java.util.EnumMap;

/**
 * Created by breez on 2016/01/22.
 */
public enum Category {
    /**放入钞票**/
    MONEY(Input.NICKEL,Input.DIME,Input.QUARTER,Input.DOLLAR),

    /**选择商品**/
    ITEM_SELECTION(Input.TOOTHPASTE,Input.CHIPS,Input.SODA,Input.SOAP),

    /**退出**/
    QUIT_TRANSACTION(Input.ABORT_TRANSACTION),

    /**关机**/
    SHUT_DOWN(Input.STOP);

    private Input[] values;


    Category(Input... types){values = types;}

    public static EnumMap<Input, Category> categories = new EnumMap<Input, Category>(Input.class);

    public Input[] getValues(){
        return values;
    }
    //初始化自动售货机状态集合
    static {
        for (Category  c : Category.class.getEnumConstants()) {
            for(Input input : c.values){
                categories.put(input, c);
            }
        }
    }

    /**返回该操作项所属状态**/
    public static Category categorize(Input input){
        return categories.get(input);
    }
}