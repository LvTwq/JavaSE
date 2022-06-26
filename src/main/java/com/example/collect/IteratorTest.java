package com.example.collect;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 当使用 Iterator 对集合元素进行迭代时，Iterator 不是把集合元素本身传给类迭代变量
 * 而是把集合元素的值传给了迭代变量
 * @author 吕茂陈
 */
public class IteratorTest {
    public static void main(String[] args) {
        HashSet<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        System.out.println(books);
        // 获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        while (it.hasNext()) {
            String book = it.next();
            System.out.println(book);
            if (book.equals("java")) {
                // 使用 iterator 迭代过程中，不可修改集合元素，下面代码将引发异常
//                books.remove(book);
                // 从集合中删除上一次 next() 方法返回的元素
                it.remove();
            }
            // 对 book 变量赋值，并不会改变集合本身
            book = "测试字符串";
        }
        System.out.println(books);
    }
}
