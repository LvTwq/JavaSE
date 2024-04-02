package com.example.multithreading.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/1/16 11:12
 */
@Slf4j
public class ScheduledExecutorServiceTest {

    public static final ScheduledExecutorService GATEWAY_SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(
            1, new ThreadFactoryBuilder().setNameFormat("THREAD-lmc-%d").build());

    public static void main(String[] args) {
        ScheduledExecutorService e = Executors.newScheduledThreadPool(0);
        e.schedule(() -> {
            log.info("业务逻辑");
        }, 6, TimeUnit.SECONDS);
        e.shutdown();

        GATEWAY_SCHEDULED_EXECUTOR.schedule(() -> {
            log.info("业务逻辑");
        }, 6, TimeUnit.SECONDS);
        e.shutdown();
    }
}
