package com.cmp.test.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by YANLL on 2017/03/22.
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
        s.add(null);
        s.add(null);
        System.out.println(s.size());
    }
}
