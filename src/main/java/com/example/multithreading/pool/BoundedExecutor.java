package com.example.multithreading.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author 吕茂陈
 * @date 2022/02/14 22:12
 */
@Slf4j
public class BoundedExecutor {

    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(() -> {
                try {
                    command.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
