package com.example.multithreading.basis;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/7 15:13
 */
public class WaitingState {
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
                        try {
                            // 进入等待的同时,会进入释放监视器
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
// t1和t2都处在WAITING (on object monitor)，进入等待状态的原因是调用了in Object.wait()。通过J.U.C包下的锁和条件队列，也是这个效果
        new Thread(task, "t1").start();
        new Thread(task, "t2").start();
    }
}
