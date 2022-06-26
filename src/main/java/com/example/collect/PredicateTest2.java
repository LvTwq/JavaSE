package com.example.collect;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class PredicateTest2 {
    public static void main(String[] args) {

        Set<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        // 统计书名包含“疯狂”子串的图书数量
        System.out.println(calAll(books, ele -> ((String) ele).contains("j")));
        System.out.println(calAll(books, ele -> ((String) ele).length() < 10));

    }

    public static int calAll(Collection books, Predicate p) {
        int total = 0;
        for (Object obj :
                books) {
            // 使用 Predicate 的 test() 方法判断对象是否满足 Predicate 指定的条件，该条件通过 Predicate 参数动态传入
            if (p.test(obj)) {
                total++;
            }
        }
        return total;
    }
}
