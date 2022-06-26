package com.example.multithreading.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/21 09:28
 */
@Slf4j
public class InitPool {

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 8, 10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        int cores = Runtime.getRuntime().availableProcessors();
        log.info("CPU 核数：{}", cores);
        int requestNum = 100;

        List<Future<?>> futureList = new ArrayList<>();

        List<Long> wholeTimeList = new ArrayList<>();
        List<Long> runTimeList = new ArrayList<>();

        for (int i = 0; i < requestNum; i++) {
            Future<?> future = threadPool.submit(new CPUTypeTest(runTimeList, wholeTimeList));
//            Future<?> future = threadPool.submit(new IOTypeTest(runTimeList, wholeTimeList));

            futureList.add(future);
        }

        for (Future<?> future : futureList) {
            // 获取线程执行结果
            future.get(requestNum, TimeUnit.SECONDS);
        }

        long wholeTime = 0;
        for (Long aLong : wholeTimeList) {
            wholeTime = aLong + wholeTime;
        }

        long runTime = 0;
        for (Long aLong : runTimeList) {
            runTime = aLong + runTime;
        }

        log.info("平均每个线程整体花费时间：{}", wholeTime / wholeTimeList.size());
        log.info("平均每个线程执行花费时间：{}", runTime / runTimeList.size());
    }
}
