package com.example.multithreading.pool;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.IntStream;

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


    @Test
    public void test02() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Snowflake snowflake = new Snowflake();
        IntStream.range(0, 10).forEach(i -> {
            executorService.submit(() -> {
                log.info(UUID.randomUUID().toString().replace("-", ""));
                log.info(snowflake.nextIdStr());
            });
        });
        log.info(UUID.randomUUID().toString());
        log.info(snowflake.nextIdStr());
    }
}
