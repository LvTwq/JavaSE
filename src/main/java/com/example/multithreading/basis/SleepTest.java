package com.example.multithreading.basis;

import java.time.LocalDateTime;

public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前时间：" + LocalDateTime.now());
            // 当前线程会进入阻塞状态，暂停1s，在其睡眠时间内，该线程不会获得执行的机会，即使系统中没有其他可执行的线程，处于sleep()中的线程也不会执行
            Thread.sleep(1000);
        }
    }
}
