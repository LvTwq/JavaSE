package com.example.collect;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author 吕茂陈
 */
public class IteratorEach {
    public static void main(String[] args) {
        HashSet<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        // 获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        // 使用 Lambda 表达式
        it.forEachRemaining(obj -> System.out.println("迭代集合元素：" + obj));
    }
}
