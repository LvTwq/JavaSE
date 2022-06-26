package com.example.multithreading.pool;

import com.example.multithreading.PrintTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author 吕茂陈
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        pool.submit(new PrintTask(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }
}
