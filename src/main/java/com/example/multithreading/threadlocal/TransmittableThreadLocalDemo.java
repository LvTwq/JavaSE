package com.example.multithreading.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/3 16:57
 */
@Slf4j
public class TransmittableThreadLocalDemo {

    private static final TransmittableThreadLocal<Integer> INHERITABLE_THREAD_LOCAL = new TransmittableThreadLocal<>();
    //模拟业务线程池
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

            threadPool.submit(TtlRunnable.get(new BusinessThread(parentThreadName)));
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
            log.info("parent:{} 的 INHERITABLE_THREAD_LOCAL 为：{}", parentThreadName, INHERITABLE_THREAD_LOCAL.get());
        }
    }
}
