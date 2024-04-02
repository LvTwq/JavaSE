package com.example.multithreading.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/3 16:42
 */
@Slf4j
public class InheritableThreadLocalWeaknessDemo {


    private static final InheritableThreadLocal<Integer> INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();
    /**
     * 线程池中即存在线程复用的情况，假设线程池中后面创建的线程中的上下文数据否都来自线程池中被复用的线程，这就出现父子线程的上下文变量复制混乱的情况。
     */
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        //模拟同时10个web请求，一个请求一个线程
        for (int i = 0; i < 10; i++) {
            new TomcatThread(i).start();
        }

        Thread.sleep(3000);
        threadPool.shutdown();
    }

    static class TomcatThread extends Thread {
        //线程下标
        int index;

        public TomcatThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            String parentThreadName = Thread.currentThread().getName();
            //父线程中将index值塞入线程上下文变量
            log.info(parentThreadName + ":" + index);
            INHERITABLE_THREAD_LOCAL.set(index);

            threadPool.submit(new BusinessThread(parentThreadName));
        }
    }

    static class BusinessThread implements Runnable {
        //父进程名称
        private String parentThreadName;

        public BusinessThread(String parentThreadName) {
            this.parentThreadName = parentThreadName;
        }

        @Override
        public void run() {
            // 子线程中输出的父线程名称与下标 index 无法一一对应，在子线程中出现出现了线程本地变量混乱的现象，在链路跟踪与全链路压测出现这种情况是致命的
            log.info("parent:{} 的 INHERITABLE_THREAD_LOCAL 为：{}", parentThreadName, INHERITABLE_THREAD_LOCAL.get());
        }
    }
}
