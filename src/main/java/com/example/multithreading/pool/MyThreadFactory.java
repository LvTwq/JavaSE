package com.example.multithreading.pool;

import java.util.concurrent.ThreadFactory;

/**
 * @author 吕茂陈
 * @date 2022/02/14 22:18
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}

