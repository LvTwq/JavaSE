package com.example.multithreading.feature;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/03/03 09:26
 */
@Slf4j
public class FutureAndCallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 使用Callable，可以获取返回值
        Callable<String> callable = () -> {
            log.info("进入 Callable 的 call 方法");
            // 模拟子线程任务，睡眠2s
            TimeUnit.SECONDS.sleep(2);
            return "来自 Callable 的 hello";
        };

        log.info("提交 Callable 到线程池");
        Future<String> future = executorService.submit(callable);

        // 如果子线程没有结束，则睡眠1s重新检查
        if (!future.isDone()) {
            log.info("子任务还没结束。。。");
            Thread.sleep(1000);
        }

        log.info("主线程继续执行！");
        log.info("主线程等待获取 Future 结果");
        if (!future.isCancelled()) {
            log.info("子线程任务已完成");
            String result = future.get();
            log.info("主线程获取到 Future 结果：{}", result);
        } else {
            log.warn("子线程任务被取消");
        }


        executorService.shutdown();
    }
}
