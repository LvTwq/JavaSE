package com.example.collect;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class TreeMapTest {

    @Test
    public void testComparable() {
        TreeMap<R, String> tm = new TreeMap<>();
        tm.put(new R(3), "java");
        tm.put(new R(9), "php");
        tm.put(new R(-5), "python");
        tm.put(new R(-4), "js");
        // 会报错
//        tm.put(null, "js");
        log.info("{}", tm);
        log.info("{}", tm.firstEntry());
        log.info("{}", tm.lastKey());
    }


    @Test
    public void testComparator() {
        TreeMap<R1, String> tm = new TreeMap<>(new R1(0));
        tm.put(new R1(3), "java");
        tm.put(new R1(9), "php");
        tm.put(new R1(-5), "python");
        tm.put(new R1(-4), "js");
        log.info("{}", tm);
    }


    @Test
    public void withOrderHashMap() {
        Map<String, String> books = new HashMap<>();
        books.put("1b345", "books");
        books.put("2adfgh", "apple");
        books.put("-1wertc", "concurrent");
        log.info("HashMap排序：{}", books);
    }


    @Test
    public void withOrderTreeMap() {
        Map<String, String> books = new TreeMap<>();
        books.put("b", "books");
        books.put("c", "concurrent");
        books.put("a", "apple");
        log.info("TreeMap排序：{}", books);
    }


    @Test
    public void test04(){
        Map<String, List<String>> typeMap = new TreeMap<>();
        typeMap.put("gjj", Lists.newArrayList("1", "2", "3", "4", "5", "6"));
        typeMap.put("sbj", Lists.newArrayList("7", "8", "9", "10", "11", "12"));
        typeMap.put("dxgn", Lists.newArrayList("14", "15", "16", "17", "18"));
        log.info("{}", typeMap);
    }


}

@AllArgsConstructor
@ToString
@EqualsAndHashCode
class R implements Comparable<R> {

    int count;

    @Override
    public int compareTo(R r) {
        return Integer.compare(count, r.count);
    }
}


@AllArgsConstructor
@ToString
@EqualsAndHashCode
class R1 implements Comparator<R1> {

    int count;

    @Override
    public int compare(R1 o1, R1 o2) {
        return Integer.compare(o1.count, o2.count);
    }
}
