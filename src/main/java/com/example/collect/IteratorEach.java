package com.example.collect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 吕茂陈
 */
@Slf4j
public class IteratorEach {


    @Test
    public void test01() {
        Set<String> uniqueDtoSet = new HashSet<>();
        System.out.println(uniqueDtoSet.add("1"));
        System.out.println(uniqueDtoSet.add("2"));
        System.out.println(uniqueDtoSet.add("1"));
    }

    public static void main(String[] args) {
        Set<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        // 获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        // 使用 Lambda 表达式
        it.forEachRemaining(obj -> log.info("迭代集合元素：{}", obj));
    }


    @Test
    public void main() {
        Set<String> books = new HashSet<>();
        books.add("javascript");
        books.add("python");
        books.add("java");

        log.info("{}", books);
        // 获取books集合对应的迭代器
        Iterator<String> it = books.iterator();
        while (it.hasNext()) {
            String book = it.next();
            log.info(book);
            if (book.equals("java")) {
                // 使用 iterator 迭代过程中，不可修改集合元素，下面代码将引发异常
//                books.remove(book);
                // 从集合中删除上一次 next() 方法返回的元素
                it.remove();
            }
            // 对 book 变量赋值，并不会改变集合本身
            book = "测试字符串";
        }
        log.info("{}", books);
    }


    @Test
    public void findFirst() {
        Set<String> strings = Set.of("1");
        // 取set中的第一个元素，需要使用迭代器
        String first = strings.iterator().next();

        // 如果Set中可能为空，需要判断
        Iterator<String> it = strings.iterator();
        if (it.hasNext()) {
            it.next();
        }

        // 使用Java8的findFirst
    }
}
