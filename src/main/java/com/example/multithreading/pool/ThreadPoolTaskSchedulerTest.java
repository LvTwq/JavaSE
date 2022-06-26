package com.example.multithreading.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/05/17 16:17
 */
@Slf4j
public class ThreadPoolTaskSchedulerTest {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Test
    public void test01() {
        FutureTask<String> task = new FutureTask<>(() -> "123");
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.execute(task);
        try {
            String s = task.get();
            log.info("使用 execute{}", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        FutureTask<String> task = new FutureTask<>(() -> "123");
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.submit(task);
        try {
            String s = task.get();
            log.info("使用 submit{}", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
