package com.example.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.util.StopWatch;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/13 12:47
 */
@Slf4j
public class StopWatchTest {


    /**
     * ArrayList是实现了基于动态数组的数据结构，而LinkedList是基于链表的数据结构
     * 对于随机访问get和set，ArrayList要优于LinkedList，因为LinkedList要移动指针
     */
    @Test
    public void testRead() {
        StopWatch stopWatch = new StopWatch();

        List<Integer> arrayList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
        stopWatch.start("ArrayList");
        arrayList.forEach(Integer::valueOf);
        stopWatch.stop();

        List<Integer> linkedList = Lists.newLinkedList(arrayList);
        stopWatch.start("LinkedList");
        linkedList.forEach(Integer::valueOf);
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

    }

    /**
     * 对于添加和删除操作add和remove，一般大家都会说LinkedList要比ArrayList快，因为ArrayList要移动数据。
     * 但是实际情况并非这样，对于添加或删除，LinkedList和ArrayList并不能明确说明谁快谁慢
     * 主要有两个因素决定他们的效率，插入的数据量和插入的位置
     */
    @Test
    public void testWrite() {
        List<Integer> arrayList = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ArrayList");
        IntStream.rangeClosed(1, 10000000).forEach(arrayList::add);
        stopWatch.stop();

        List<Integer> linkedList = new LinkedList<>();
        stopWatch.start("LinkedList");
        IntStream.rangeClosed(1, 10000000).forEach(linkedList::add);
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

    }




}
