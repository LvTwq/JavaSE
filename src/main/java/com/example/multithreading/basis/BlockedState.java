package com.example.multithreading.basis;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/7 15:07
 */
public class BlockedState {
    private static Object object = new Object();

    public static void main(String[] args) {
        Runnable task = new Runnable() {

            @Override
            public void run() {
                synchronized (object) {
                    long begin = System.currentTimeMillis();

                    long end = System.currentTimeMillis();

                    // 让线程运行5分钟,会一直持有object的监视器
                    while ((end - begin) <= 5 * 60 * 1000) {

                    }
                }
            }
        };

        // 先获取object的线程会执行5分钟，这5分钟内会一直持有object的监视器，另一个线程无法执行处在BLOCKED状态
        new Thread(task, "t1").start();
        new Thread(task, "t2").start();
    }
}
