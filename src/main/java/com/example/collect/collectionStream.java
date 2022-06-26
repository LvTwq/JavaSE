package com.example.collect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author 吕茂陈
 */
public class collectionStream {
    public static void main(String[] args) {
        HashSet<String> books = new HashSet<>();
        books.add("java");
        books.add("php");
        books.add("c++");

        // 统计书名包含“java”子串的图书数量
        System.out.println(books.stream().filter(ele -> ele.contains("java")).count());

        // 统计书名长度大于2的图书数量
        System.out.println(books.stream().filter(ele -> ele.length() > 2).count());

        // 先调用Collection对象的 stream() 方法，将集合转换成 Stream
        // 再调用Stream() 的 mapToInt() 获取原有的Steam对应的 IntStream
        // mapToInt()是中间方法，所以可以继续调用 forEach() 来遍历流中的元素
        books.stream().mapToInt(String::length).forEach(System.out::println);
    }


    @Test
    public void test01(){

        List<String> list = new ArrayList<>();
        List<String> collect = list.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
        System.out.println(collect);

        String s = list.stream().findFirst().orElse("3");
        System.out.println(s);
    }
}
