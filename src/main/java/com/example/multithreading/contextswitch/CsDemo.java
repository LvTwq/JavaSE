package com.example.multithreading.contextswitch;

import org.springframework.util.StopWatch;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程上下文切换
 *
 * @author 吕茂陈
 * @date 2022/02/23 09:16
 */
@Slf4j
public class CsDemo {


    static abstract class ThreadContextSwitchTester {
        public static final int count = 100000000;

        @Getter
        public volatile int counter = 0;

        public void increaseCounter() {
            this.counter += 1;
        }

        public abstract void start();
    }


    static class MultiThreadTester extends ThreadContextSwitchTester {

        @Override
        public void start() {
            StopWatch stopWatch = new StopWatch();

            MyRunnable myRunnable1 = new MyRunnable();
            Thread[] threads = new Thread[4];
            // 创建多个线程
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(myRunnable1);
                threads[i].start();
            }

        }

        class MyRunnable implements Runnable {
            @Override
            public void run() {
                while (counter < 100000000) {
                    synchronized (this) {
                        if (counter < 100000000) {
                            increaseCounter();
                        }
                    }
                }
            }
        }
    }
}
