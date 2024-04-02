package com.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/5/24 14:02
 */
@Slf4j
public class ListenableFutureTest {


    public static void main(String[] args) {
        // 创建一个异步任务
        ListenableFutureTask<String> futureTask = new ListenableFutureTask<>(() -> {
            // 异步操作逻辑
            Thread.sleep(1000);
            throw new RuntimeException("异常");
//            return "Hello, ListenableFuture!";
        });

        // 注册监听器
        futureTask.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("异步操作成功，结果为: " + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("异步操作失败，异常信息为: " + ex.getMessage());
            }
        });

        // 执行异步任务
//        TaskExecutor executor = new SimpleAsyncTaskExecutor();
//        executor.execute(futureTask);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(futureTask);
    }
}
