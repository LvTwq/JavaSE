package com.example.multithreading.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 内存泄漏
 *
 * @author 吕茂陈
 * @date 2022/03/26 16:07
 */
@Slf4j
public class ThreadLocalDemo {
    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

    static final ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000 * 4);
        for (int i = 0; i < 50; ++i) {
            poolExecutor.execute(() -> {

                localVariable.set(new LocalVariable());

                log.info("使用本地变量: {}", localVariable.get());
//                localVariable.remove();
            });
        }

        log.info("pool execute over");
    }
}

