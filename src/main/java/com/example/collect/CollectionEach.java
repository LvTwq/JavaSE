package com.example.collect;

import java.util.HashSet;

/**
 * @author 吕茂陈
 */
public class CollectionEach {

    public static void main(String[] args) {
        HashSet<Object> books = new HashSet<>();
        books.add("java");
        books.add("python");
        books.add("javascript");
        books.forEach(obj -> System.out.println(obj));
    }
}
