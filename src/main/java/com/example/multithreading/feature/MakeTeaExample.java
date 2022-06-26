package com.example.multithreading.feature;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/03/05 13:23
 */
@Slf4j
public class MakeTeaExample {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("test01");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        FutureTask<String> ft1 = new FutureTask<>(new T1Task());
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());

        executorService.submit(ft1);
        executorService.submit(ft2);

        // 主线程等待两个FutureTask 的执行结果
        log.info(ft1.get() + ft2.get());
        log.info("开始泡茶！");

        executorService.shutdown();

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("test02");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        FutureTask<String> ft1 = new FutureTask<>(new T1TaskPro(ft2));

        executorService.submit(ft1);
        executorService.submit(ft2);

        executorService.shutdown();

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    static class T1TaskPro implements Callable<String> {

        private FutureTask<String> ft2;

        public T1TaskPro(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            log.info("T1：洗水壶....");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1：烧开水....");
            TimeUnit.SECONDS.sleep(15);

            String t2Result = ft2.get();
            log.info("T1拿到T2的 {}，开始泡茶", t2Result);

            return "T1：上茶";
        }
    }


    static class T1Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("T1：洗水壶....");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1：烧开水....");
            TimeUnit.SECONDS.sleep(15);

            return "T1：开水已备好！";
        }
    }

    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("T2：洗茶壶....");
            TimeUnit.SECONDS.sleep(1);

            log.info("T2：洗茶杯....");
            TimeUnit.SECONDS.sleep(2);

            log.info("T2：拿茶叶....");
            TimeUnit.SECONDS.sleep(1);

            return "T2：茶叶已备好！";
        }
    }
}
