package com.example.multithreading.count;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/28 11:49
 */
public class ParallelFetcher {

    final long timeout;
    final CountDownLatch latch;
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 1, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(100));

    public ParallelFetcher(int jobSize, long timeoutMill) {
        latch = new CountDownLatch(jobSize);
        timeout = timeoutMill;
    }

    public void submitJob(Runnable runnable) {
        executor.execute(() -> {
            runnable.run();
            latch.countDown();
        });
    }

    public void await() {
        try {
            this.latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void dispose() {
        this.executor.shutdown();
    }
}
