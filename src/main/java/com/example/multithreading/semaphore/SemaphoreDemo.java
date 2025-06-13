package com.example.multithreading.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/8/12 14:43
 */
@Slf4j
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread threadA = new Thread(new MyRunnable(1, semaphore, reentrantLock), "thread-A");
        Thread threadB = new Thread(new MyRunnable(2, semaphore, reentrantLock), "thread-B");
        Thread threadC = new Thread(new MyRunnable(1, semaphore, reentrantLock), "thread-C");
        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class MyRunnable implements Runnable {
        private int n;
        private Semaphore semaphore;
        private ReentrantLock lock;

        public MyRunnable(int n, Semaphore semaphore, ReentrantLock lock) {
            this.n = n;
            this.semaphore = semaphore;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(n);
                // 应该使用 availablePermits
                log.info("剩余可用许可证: {}", semaphore.drainPermits());
                log.info("{} 执行完成。。。。", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                log.error("报错", e);
            } finally {
                semaphore.release(n);
            }
        }
    }
}
