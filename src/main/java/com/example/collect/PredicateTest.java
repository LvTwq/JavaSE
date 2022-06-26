package com.example.collect;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 吕茂陈
 */
public class PredicateTest {
    public static void main(String[] args) {
        Set<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        // 批量删除符合条件的所有元素
        books.removeIf(ele -> ele.length() < 10);
        System.out.println(books);
    }
}
