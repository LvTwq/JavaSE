package com.example.collect;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

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
        log.info("{}", calAll(books, ele -> ((String) ele).contains("j")));
        log.info("{}", calAll(books, ele -> ((String) ele).length() < 10));

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


//    @Test
//    public void test01(){
//        List<String> correct = List.of("1","2","3");
//        List<String> err = List.of("1");
//        List<String> list = correct.stream().filter(d -> separateErr(e -> err.contains(e))).collect(Collectors.toList());
//        log.info("{}", list);
//    }
//
//
//    private Predicate<String> separateErr(Function<String, Boolean> errExtractor) {
//        return errExtractor::apply;
//    }
}
