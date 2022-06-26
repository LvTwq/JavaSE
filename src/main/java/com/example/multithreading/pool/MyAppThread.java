package com.example.multithreading.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吕茂陈
 * @date 2022/02/14 22:20
 */
@Slf4j
public class MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "MyAppThread";

    private static volatile boolean debugLifecycle = false;

    private static final AtomicInteger created = new AtomicInteger();

    private static final AtomicInteger alive = new AtomicInteger();

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable, String name) {
        super(runnable, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(
                (t, e) -> log.error("在线程【{}】中未被捕获", t.getName()
                        , e)
        );
    }

    @Override
    public void run() {
        // 复制 debug 标志，以确保一致的值
        boolean debug = debugLifecycle;
        if (debug) {
            log.info("创建【{}】", getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.info("退出【{}】", getName());
            }
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadsAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
