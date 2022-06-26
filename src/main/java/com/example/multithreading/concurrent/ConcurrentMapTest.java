package com.example.multithreading.concurrent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/10 08:54
 */
@Slf4j
public class ConcurrentMapTest {

    /**
     * 线程个数
     */
    private static int THREAD_COUNT = 10;

    /**
     * 总元素数量
     */
    private static int ITEM_COUNT = 1000;


    /**
     * 在每一个线程的代码逻辑中先通过size方法拿到当前元素的数量，计算 ConcurrentHashMap目前还需要补充多少元素，然后通过putAll方法补充缺少的元素
     *
     * @return
     * @throws InterruptedException
     */
    @Test
    public void test01() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        // 初始化900个元素
        log.info("初始化元素数量{}", concurrentHashMap.size());

        // 注意打印出来的日志的线程名
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // 使用线程池并发处理
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            // 加锁
            synchronized (concurrentHashMap) {
                int gap = ITEM_COUNT - concurrentHashMap.size();
                log.info("gap 大小：{}", gap);
                // 补充元素
                concurrentHashMap.putAll(getData(gap));
            }

        }));
        // 平滑的关闭ExecutorService，调用此方法时，ExecutorService停止接收新的任务，并且等待所有任务（提交正在执行、提交未执行）完成，当所有任务执行完毕，线程池被关闭
        forkJoinPool.shutdown();
        // 用于设定超时时间即单位，当等待超过设定时间时，会监测ExecutorService是否已经关闭，关闭返回true，否则返回false，一般和shutdown()方法组合使用
        if (forkJoinPool.awaitTermination(1, TimeUnit.HOURS)) {
            log.info("线程池关闭！");
        } else {
            log.error("线程池没有关闭！");
        }
        log.info("完成后，大小：{}", concurrentHashMap.size());
    }


    /**
     * 用于获取一个指定元素数量模拟数据的 ConcurrentHashMap
     *
     * @param count
     * @return
     */
    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }


    /**
     * 循环次数
     */
    private static int LOOP_COUNT = 10000000;


    @Test
    public void test02() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normalUse");
        Map<String, Long> normalUse = normalUse();
        stopWatch.stop();
        // 校验元素数量
        Assert.isTrue(normalUse.size() == ITEM_COUNT, "normalUse 大小错误！");
        // 校验累计总数
        Assert.isTrue(normalUse.values().stream().mapToLong(
                l -> l).reduce(0, Long::sum) == LOOP_COUNT
                , "normalUse 计算错误！");

        stopWatch.start("goodUse");
        Map<String, Long> goodUse = goodUse();
        stopWatch.stop();
        Assert.isTrue(goodUse.size() == ITEM_COUNT, "goodUse 大小错误！");
        Assert.isTrue(goodUse.values().stream().mapToLong(
                l -> l).reduce(0, Long::sum) == LOOP_COUNT
                , "goodUse 计算错误！");

        log.info(stopWatch.prettyPrint());
    }

    private Map<String, Long> normalUse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
            // 获取一个随机的key
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            // 直接锁住Map，然后判断、读取现在的累计值、加1、保存累加后值，无法充分发挥 ConcurrentHashMap 的威力
            synchronized (freqs) {
                if (freqs.containsKey(key)) {
                    // freqs 存在则+1
                    freqs.put(key, freqs.get(key) + 1);
                } else {
                    freqs.put(key, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        if (forkJoinPool.awaitTermination(1, TimeUnit.HOURS)) {
            log.info("线程池关闭！");
        } else {
            log.error("线程池没有关闭！");
        }
        return freqs;
    }


    /**
     * computeIfAbsent 性能更好，使用了 Java 自带的 Unsafe 实现的 CAS，它在虚拟机层面确保了数据的原子性，比加锁效率高
     *
     * @return
     * @throws InterruptedException
     */
    private Map<String, Long> goodUse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel()
                .forEach(i -> {
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    //利用computeIfAbsent()方法来实例化LongAdder，然后利用LongAdder来进行线程安全计数
                    freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
                })
        );
        forkJoinPool.shutdown();
        if (forkJoinPool.awaitTermination(1, TimeUnit.HOURS)) {
            log.info("线程池关闭！");
        } else {
            log.error("线程池没有关闭！");
        }
        //因为我们的Value是LongAdder而不是Long，所以需要做一次转换才能返回
        return freqs.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().longValue())
        );
    }


}
