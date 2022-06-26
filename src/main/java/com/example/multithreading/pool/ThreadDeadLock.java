package com.example.multithreading.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/12 15:16
 */
@Slf4j
public class ThreadDeadLock {

    ExecutorService exec = Executors.newSingleThreadExecutor();

    class RenderPageTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Future<String> header = exec.submit(new LoadFileTask("header.html"));
            Future<String> footer = exec.submit(() -> "foot.html");
            // 死锁 —— 任务在等待子任务的结果
            return header.get() + footer.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        FutureTask<String> task = new FutureTask<>(() -> {
            Future<String> header = exec.submit(() -> "header.html");
            Future<String> foot = exec.submit(() -> "foot.html");
            return String.join(", ", header.get(), foot.get());
        });
        new Thread(task, "返回值").start();
        log.info(task.get());
    }
}

class LoadFileTask implements Callable<String> {

    public final String str;

    public LoadFileTask(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return str;
    }
}
