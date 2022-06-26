package com.example.multithreading.exception;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/05/10 16:33
 */
@Slf4j
public class PoolException {


    @Test
    public void execute() throws InterruptedException {
        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1,
                new ThreadFactoryBuilder().setNameFormat(prefix + "%d").build());
        // 提交10个任务到线程池处理，第五个任务会抛出运行时异常
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(() -> {
            if (i == 5) {
                throw new RuntimeException("第五个任务异常");
            }
            /*
            任务1~4所在的线程是test0，任务6开始运行在test1
            因此可知因为异常的抛出，老线程退出了，线程池只能重新创建一个线程
            如果每个异步任务都以异常结束，那么线程池可能完全起不到线程重用的作用
             */
            log.info("我完成了：{}", i);
        }));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    @Test
    public void executeRight01() throws InterruptedException {
        // 设置异常处理
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1,
                new ThreadFactoryBuilder()
                        .setNameFormat(prefix + "%d")
                        .setUncaughtExceptionHandler((thread, throwable) -> log.error("线程池中的{}线程异常！", thread, throwable))
                        .build());
        // 提交10个任务到线程池处理，第五个任务会抛出运行时异常
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(() -> {
            if (i == 5) {
                throw new RuntimeException("第五个任务异常");
            }
            log.info("我完成了：{}", i);
        }));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    @Test
    public void testSubmit() {
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1,
                new ThreadFactoryBuilder()
                        .setNameFormat(prefix + "%d")
                        .setUncaughtExceptionHandler((thread, throwable) -> log.error("线程池中的{}线程异常！", thread, throwable))
                        .build());
        // 使用submit代表关心任务执行结果
        List<Future> tasks = IntStream.rangeClosed(1, 10).mapToObj(i -> threadPool.submit(() -> {
            if (i == 5) {
                throw new RuntimeException("第五个任务异常");
            }
            log.info("我完成了：{}", i);
        })).collect(Collectors.toList());

        tasks.forEach(task -> {
            try {
                // 使用了submit，就一定要使用get
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
