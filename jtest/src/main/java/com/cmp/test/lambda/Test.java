package com.cmp.test.lambda;//package com.cmp.lambda;

import com.cmp.common.utils.UtilVelocity;
import org.apache.velocity.VelocityContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by YANLL on 2016/07/12.
 */
public class Test {
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    /*@org.junit.Test*/
    public void predicateT() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /*@org.junit.Test*/
    public void functionT() {
        Function<String, Integer> toInteger = Integer::valueOf;


        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123456");     // "123"
    }

    /*@org.junit.Test*/
    public void test() {
        Converter<String, Integer> converter = Integer::valueOf;
        System.out.println(converter.convert("123456"));


        List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        /*friends.forEach(name -> System.out.println(name));*/
    }

    public static void main(String[] args) throws Exception {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        UtilVelocity.write("goods_detail.vm", "/var/velocity", "goods_detail.html", context -> {
            context.put("title", "商品详情");
            context.put("content", "商品详情...");
        });

        UtilVelocity.write("goods_detail.vm", "/var/velocity", "goods_detail.html", (context) -> {
            context.put("title", "商品详情");
            context.put("content", "商品详情...");
        });

        UtilVelocity.write("goods_detail.vm", "/var/velocity", "goods_detail.html", (VelocityContext context) -> {
            context.put("title", "商品详情");
            context.put("content", "商品详情...");
        });

    }


}
