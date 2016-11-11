package com.cmp.test.sdk.enums;


import com.yanll.framework.util.enums.EnumUtil;
import com.yanll.framework.util.enums.IEnum;

import java.util.Map;

/**
 * Created by YLL on 2015/08/06.
 */
public class Main {
    public static void main(String[] args) {


        IKeys.Cache cache = IKeys.Cache.IndexData;

        System.out.println(cache.name());
        System.out.println(cache.toString());
        System.out.println(cache.getDeclaringClass().getSimpleName());
        System.out.println(cache.getClass().getName());
        System.out.println(Enum.valueOf(IKeys.Cache.class, "IndexData").getMemo());
        cache.doit();


        Map<Integer, String> map = EnumUtil.toMap(IEnum.YESNO.class);
        System.out.println(map);


    }
}
