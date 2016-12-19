package com.cmp.test.lambda;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/12/17.
 */
public class Collection {

    @org.junit.Test
    public void ass() {
        List<String> list = Lists.newArrayList("a", "b");
        //long count = list.stream().filter(e -> e.equals("a")).count();
        list.stream().filter(e -> e.equals("a")).collect(Collectors.toList());
        Stream.of("");
    }


}
