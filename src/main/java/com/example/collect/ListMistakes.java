package com.example.collect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StopWatch;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/05/09 16:49
 */
@Slf4j
public class ListMistakes {


    private static List<List<Integer>> data = new ArrayList<>();


    @Test
    public void oom() {
        for (int i = 0; i < 100000; i++) {
            // rawList 始终被sub强引用，不会被回收
            List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
            List<Integer> sub = rawList.subList(0, 1);
            data.add(sub);
            log.info("sub ：{}", sub.get(0));
            log.info("data 长度：{}", data.size());
        }
    }


    @Test
    public void test01() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = list.subList(1, 4);
        log.info("子 list：{}", subList);
        // 原始list中的数字3也被删除了，说明删除子list影响到了原始List
        subList.remove(1);
        log.info("删除子list后的，原始list：{}", list);
        list.add(0);
        try {
            log.info("原始list添加元素后，子 list：{}", subList);
//            subList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Order {
        private int orderId;
    }


    private Object listSearch(int elementCount, int loopCount) {
        List<Order> list = IntStream.rangeClosed(1, elementCount).mapToObj(i -> new Order(i)).collect(Collectors.toList());
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            // 随机搜索一个订单号
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = list.stream().filter(order -> order.getOrderId() == search).findFirst().orElse(null);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return list;
    }


    private Object mapSearch(int elementCount, int loopCount) {
        Map<Integer, Order> map = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toMap(Function.identity(), i -> new Order(i)));
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = map.get(search);
            Assert.assertTrue(result != null && result.getOrderId() == search);
        });
        return map;
    }

    /**
     * map 时间占优
     * list 空间占优
     */
    @Test
    public void test02() {
        int elementCount = 1000000;
        int loopCount = 1000;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("listSearch");
        Object list = listSearch(elementCount, loopCount);
        log.info("list 大小：{}", ObjectSizeCalculator.getObjectSize(list));
        stopWatch.stop();

        stopWatch.start("mapSearch");
        Object map = mapSearch(elementCount, loopCount);
        stopWatch.stop();

        log.info("map 大小：{}", ObjectSizeCalculator.getObjectSize(map));
        log.info("{}", stopWatch.prettyPrint());
    }


    /**
     * LinkedList随机访问
     *
     * @param elementCount
     * @param loopCount
     */
    private void linkedListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        // 从 (1,elementCount) 取一个随机数作为下标，访问list中的这个元素
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    /**
     * ArrayList随机访问
     *
     * @param elementCount
     * @param loopCount
     */
    private void arrayListGet(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    /**
     * LinkedList随机插入
     *
     * @param elementCount
     * @param loopCount
     */
    private void linkedListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }

    /**
     * ArrayList随机插入
     *
     * @param elementCount
     * @param loopCount
     */
    private void arrayListAdd(int elementCount, int loopCount) {
        List<Integer> list = IntStream.rangeClosed(1, elementCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount).forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }


    @Test
    public void test03() {
        int elementCount = 100000;
        int loopCount = 100000;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("linkedListGet");
        linkedListGet(elementCount, loopCount);
        stopWatch.stop();

        stopWatch.start("arrayListGet");
        arrayListGet(elementCount, loopCount);
        stopWatch.stop();

        log.info("随机访问速度对比：{}", stopWatch.prettyPrint());


        StopWatch stopWatch2 = new StopWatch();

        stopWatch2.start("linkedListAdd");
        linkedListAdd(elementCount, loopCount);
        stopWatch2.stop();

        stopWatch2.start("arrayListAdd");
        arrayListAdd(elementCount, loopCount);
        stopWatch2.stop();

        log.info("随机插入速度对比：{}", stopWatch2.prettyPrint());
    }
}
