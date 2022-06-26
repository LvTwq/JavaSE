package com.example.collect;

import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 */
public class IntStreamTest {
    public static void main(String[] args) {
        IntStream is = IntStream.builder().add(20).add(13).add(-2).add(18).build();
//        System.out.println(is.max().getAsInt());
//        System.out.println(is.min().getAsInt());
//
//        System.out.println(is.sum());
//        System.out.println(is.count());
//        System.out.println(is.average());

        // 将is映射成一个新Stream，map() 是一个中间方法，返回值是另外一个流
        IntStream newIs = is.map(ele -> ele * 2 + 1);
        newIs.forEach(System.out::println);

    }
}
