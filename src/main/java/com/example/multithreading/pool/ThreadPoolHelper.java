package com.example.multithreading.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.experimental.UtilityClass;

/**
 * @author 吕茂陈
 * @date 2022/02/17 09:02
 */
@UtilityClass
public class ThreadPoolHelper {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 2,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build());

    /**
     * 如果每次都创建一个这样的线程池，最终不能被回收，会出现OOM：
     * 即使自定义线程池，核心线程是不会回收的，如果每次需要10个线程，刚好是核心线程数，每次请求都会创建10个核心线程数的线程池
     * <p>
     * ThreadPoolExecutor 不会被垃圾回收，工作线程Worker是内部类，只要它活着（线程在跑），就会阻止 ThreadPoolExecutor 回收，
     * 所以 ThreadPoolExecutor 是无法回收的，不能认为 ThreadPoolExecutor 没有引用就能回收
     *
     * @return
     */
    public static ThreadPoolExecutor getRightThreadPool() {
        return threadPoolExecutor;
    }


    public static ThreadPoolExecutor getWrongThreadPool() {
        // 线程池没有复用
        return (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
}
