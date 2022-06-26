package com.example.collect;

import java.util.HashSet;

/**
 * 使用 ForeachTest 循环遍历集合元素
 *
 * @author 吕茂陈
 */
public class ForeachTest {
    public static void main(String[] args) {
        HashSet<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        for (String obj :
                books) {
            // 这里的 book 变量不是集合元素本身，只是集合元素的值
            String book = obj;
            System.out.println(book);
            if (book.equals("java")) {
                // 异常
                books.remove(book);
            }
        }
    }
}
