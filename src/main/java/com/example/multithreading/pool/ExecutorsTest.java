package com.example.multithreading.pool;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/13 18:09
 */
@Slf4j
public class ExecutorsTest {

    @Test
    public void oom1() throws InterruptedException {
        /*
         翻看 newFixedThreadPool 方法的源码不难发现，线程池的工作队列直接 new 了一个 LinkedBlockingQueue，
         而默认构造方法的 LinkedBlockingQueue 是一个 Integer.MAX_VALUE 长度的队列，可以认为是无界的
         虽然使用 newFixedThreadPool 控制了固定线程数量，但任务队列是无界的，如果任务较多并且执行慢的话，队列可能会快速积压，撑爆内存
         */
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        printStats(threadPool);
        // 循环 1 亿次向线程池提交任务，每个任务都会创建一个比较大的字符串然后休眠一小时
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String payLoad = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(e -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(payLoad);
            });
        }

        threadPool.shutdown();
        if (threadPool.awaitTermination(1, TimeUnit.HOURS)) {
            log.info("线程池关闭！");
        } else {
            log.error("线程池没有关闭！");
        }
    }

    @Test
    public void oom2() throws InterruptedException {
        /*
         最大线程数是Integer.MAX_VALUE，而其工作队列 SynchronousQueue 是一个没有存储空间的阻塞队列。
         这意味着，只要有请求到来，因为当前没有空闲的线程，需要创建一条新的
         线程是需要分配一定的内存空间作为线程栈的，比如 1MB，因此无限制创建线程必然会导致 OOM
         */
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        printStats(threadPool);
        // 循环 1 亿次向线程池提交任务，每个任务都会创建一个比较大的字符串然后休眠一小时
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String payLoad = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(e -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(payLoad);
            });
        }

        threadPool.shutdown();
        if (threadPool.awaitTermination(1, TimeUnit.HOURS)) {
            log.info("线程池关闭！");
        } else {
            log.error("线程池没有关闭！");
        }
    }


    private void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("线程池大小: {}", threadPool.getPoolSize());
            log.info("活跃线程数: {}", threadPool.getActiveCount());
            log.info("完成的任务数: {}", threadPool.getCompletedTaskCount());
            log.info("队列中的任务数: {}", threadPool.getQueue().size());
            log.info("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }


    @Test
    public void right() throws InterruptedException {
        // 计数器：用于跟踪完成的任务数
        AtomicInteger atomicInteger = new AtomicInteger();
        //创建一个具有2个核心线程、5个最大线程，使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池，使用默认的AbortPolicy拒绝策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        printStats(threadPool);
        // 每隔一秒提交一次，一共提交20次
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {
                threadPool.submit(() -> {
                    log.info("{} 开始", id);
                    // 每个任务耗时10秒
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{} 完成", id);
                });
            } catch (Exception e) {
                // 提交出现异常的话，打印出错信息并为计数器减一
                log.error("提交任务{}错误", id, e);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(60);
        log.info("atomicInteger 值为：{}", atomicInteger.intValue());
    }


    @Test
    public void test03() {
        // 只有 2 个核心线程，最大线程也是 2，使用了容量为 100 的 ArrayBlockingQueue 作为工作队列，使用了 CallerRunsPolicy 拒绝策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("批量文件处理-threadpool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        printStats(threadPool);
        // 模拟文件批处理，通过一个线程开启死循环逻辑，不断地向线程池提交任务，任务的逻辑是向一个文件中写入大量的数据
        new Thread(() -> {
            // 模拟需要写入大量的数据
            String payload = IntStream.rangeClosed(1, 1_000_000)
                    .mapToObj(i -> "a")
                    .collect(Collectors.joining(""));
            while (true) {
                threadPool.execute(() -> {
                    try {
                        Files.write(Paths.get("spFilePath/demo.txt"), Collections.singletonList(LocalTime.now().toString() + ":" + payload),
                                StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.info("批量文件处理完成");
                });
            }
        }).start();
    }


    @Test
    public void test04() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("批量文件处理-threadpool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Future<Integer> submit = threadPool.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(10);
            return 1;
        });
        log.info("任务结果为：{}", submit.get());
    }
}
