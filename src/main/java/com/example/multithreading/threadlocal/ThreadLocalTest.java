package com.example.multithreading.threadlocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.util.StopWatch;

import com.example.multithreading.Account;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ThreadLocalTest {

    @Test
    public void test01() {
        Account at = new Account("初始名");
        /*
         * 两个线程共享同一账户，即只有一个账户名
         * 但由于账户名是ThreadLocal类型，所以每个线程都完全拥有各自的账户名副本
         * 因此在i==6之后，将看到两个线程访问同一个账户时出现不同的账户名
         */
        new MyTest(at, "线程甲").start();
        new MyTest(at, "线程乙").start();
    }


    /**
     * ThreadLocalRandom 是 ThreadLocal 类和 Random 类的组合，它与当前线程隔离，通过简单地避免对 Random 对象的任何并发访问，在多线程环境中实现了更好的性能。
     * <p>
     * 也就是说，相比于 java.util.Random 类全局的提供随机数生成， 使用 ThreadLocalRandom，一个线程获得的随机数不受另一个线程的影响。
     */
    @Test
    public void test02() {
        // 拿到当先线程的 ThreadLocalRandom 实例，生成一个没有任何边界的随机 int 值（其实边界是int的边界）
        int i = ThreadLocalRandom.current().nextInt();
        log.info("无边界：{}", i);
        // 这个实例生成的随机数在 [0,100) 之间
        int i1 = ThreadLocalRandom.current().nextInt(0, 100);
        log.info("[0,100) 之间：{}", i1);
        // 从生成器序列中生成下一个正态分布的值，其值范围在 0.0 和 1.0 之间
        double v = ThreadLocalRandom.current().nextGaussian();
        log.info("0.0 和 1.0 之间:{}", v);
    }


    /**
     * ThreadLocalRandom 更快
     *
     * @throws InterruptedException
     */
    @Test
    public void test03() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<Integer>> callableList1 = new ArrayList<>();
        List<Callable<Integer>> callableList2 = new ArrayList<>();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("random");
        Random random = new Random();
        IntStream.rangeClosed(1, 10000).forEach(
                e -> callableList1.add(
                        random::nextInt
                )
        );
        log.info("callableList1 大小：{}", callableList1.size());
        stopWatch.stop();

        stopWatch.start("ThreadLocalRandom");
        IntStream.rangeClosed(1, 10000).forEach(
                e -> callableList2.add(
                        () -> ThreadLocalRandom.current().nextInt()
                )
        );
        log.info("callableList2 大小：{}", callableList2.size());
        stopWatch.stop();

        executor.invokeAll(callableList1);
        executor.invokeAll(callableList2);
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void test04() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        StopWatch stopWatch = new StopWatch();
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

        stopWatch.start("random");
        IntStream.rangeClosed(1, 10000).forEach(
                e -> forkJoinPool.execute(() -> list1.add(ThreadLocalRandom.current().nextInt()))
        );
        log.info("list1 大小：{}", list1.size());
        stopWatch.stop();

        stopWatch.start("ThreadLocalRandom");
        Random random = new Random();
        IntStream.rangeClosed(1, 10000).forEach(
                e -> forkJoinPool.execute(() -> list2.add(random.nextInt()))
        );
        log.info("list2 大小：{}", list2.size());
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
    }
}
