package com.example.lambda;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author 吕茂陈
 */
public class CollectionEach {
    public static void main(String[] args) {
        HashSet<String> books = new HashSet<>();
        books.add("java");
        books.add("php");
        books.add("c++");
        // Lambda表达式遍历集合
        books.forEach(obj -> System.out.println(obj));

        Iterator<String> it = books.iterator();
        // Lambda 遍历Iterator
        it.forEachRemaining(obj -> System.out.println(obj));


//        while (it.hasNext()) {
//            String book = it.next();
//            System.out.println(book);
//            if (book.equals("php")) {
//                it.remove();
//            }
//            // Iterator 没有盛装对象的能力，故赋值无效
//            book = "测试字符串";
//        }
//        System.out.println(books);


    }
}
