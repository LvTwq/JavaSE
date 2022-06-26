package com.example.collect;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 吕茂陈
 */
public class LinkedHashSetTest {
    public static void main(String[] args) {
        Set<String> books = new LinkedHashSet();
        books.add("javascript");
        books.add("python");
        books.add("java");

        System.out.println(books);
        books.remove("java");
        System.out.println(books);
    }
}
