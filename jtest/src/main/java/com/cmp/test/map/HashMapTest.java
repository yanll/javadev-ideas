package com.cmp.test.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YANLL on 2017/03/22.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("b", "bbbb");
        map.put("c", null);
        map.put(null, null);
        map.put(null, "n");

        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

    }
}
