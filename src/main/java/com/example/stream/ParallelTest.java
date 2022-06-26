package com.example.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * parallelStream 是一种并行流, 意思为处理任务时并行处理（前提是硬件支持, 如果单核 CPU, 只会存在并发处理, 而不会并行）
 * <p>
 * 并行流底层就是使用的 ForkJoinPool, 一种 工作窃取算法线程池
 * ForkJoinPool 的优势在于, 可以充分利用多 CPU 的优势，把一个任务拆分成多个"小任务", 把多个"小任务"放到多个处理器核心上并行执行; 当多个"小任务"执行完成之后, 再将这些执行结果合并起来
 * <p>
 * ForkJoinPool 实例内部线程总数 parallelism 默认为: 当前运行环境的 CPU 核数 - 1
 *
 * @author 吕茂陈
 * @date 2022/02/12 17:12
 */
@Slf4j
public class ParallelTest {

    @Test
    public void test01() {
        log.info("==========乱序输出==========");
        IntStream.rangeClosed(1, 10)
                .parallel()
                // 注意打印出来的线程名：ForkJoinPool.commonPool-worker-
                .forEach(e -> log.info("{}", e));
        log.info("==========顺序输出==========");
        IntStream.rangeClosed(1, 10)
                .forEach(e -> log.info("{}", e));
    }

    @Test
    public void test02() {
        List<Integer> integerList = new LinkedList<>();
        IntStream.rangeClosed(1, 1000000)
                .parallel()
                .forEach(integerList::add);
        /*
         integerList 按道理应该长度为：1000000
         但并不会有这么长，因为并行流处理是多线程操作，会导致 integerList 线程不安全
         实际长度也不固定，取决于CPU的具体处理速度
         */
        log.info("实际长度：{}", integerList.size());

    }

    /**
     * 如果使用parallel()，则需要线程安全的 list，否则 java.lang.ArrayIndexOutOfBoundsException
     */
    @Test
    public void test03() {
        List<Integer> arrayList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ArrayList");
        IntStream.rangeClosed(1, 1000000).parallel().forEach(arrayList::add);
        stopWatch.stop();
        log.info("arrayList 大小：{}", arrayList.size());

        List<Integer> linkedList = Collections.synchronizedList(new LinkedList<>());
        stopWatch.start("LinkedList");
        IntStream.rangeClosed(1, 1000000).parallel().forEach(linkedList::add);
        stopWatch.stop();
        log.info("linkedList 大小：{}", linkedList.size());

        log.info(stopWatch.prettyPrint());

    }


    @Test
    public void test04() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("使用并行流+锁");
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
        IntStream.rangeClosed(1, 1000000).parallel().forEach(list1::add);
        log.info("list1 大小：{}", list1.size());
        stopWatch.stop();

        stopWatch.start("使用串行流");
        List<Integer> list2 = new ArrayList<>();
        IntStream.rangeClosed(1, 1000000).forEach(list2::add);
        log.info("list2 大小：{}", list2.size());
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
    }


}
