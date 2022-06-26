package com.example.multithreading.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/13 17:19
 */
@Slf4j
public class ThreadPoolExecutorTest {

    @Test
    public void test01() {
        //maximumPoolSize设置为2 ，拒绝策略为AbortPolicy策略，直接抛出异常
        ExecutorService pool = new ThreadPoolExecutor(1, 2, 1000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.AbortPolicy());
        IntStream.rangeClosed(0, 2).forEach(e -> pool.execute(
                () -> log.info("当前数字：{}", e)
        ));
    }
}
