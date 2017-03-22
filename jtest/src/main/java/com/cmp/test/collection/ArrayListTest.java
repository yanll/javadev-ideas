package com.cmp.test.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANLL on 2017/03/22.
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayListTest t = new ArrayListTest();
        t.andSubList();
    }


    void andSubList() {
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("guest");
        list.add("user");
        list.add(null);

        List<String> subList = list.subList(0, 2);

        for (String s : subList) {
            s += "_";
        }
        for (String s : list) {
            System.out.println(s);
        }

    }
}
