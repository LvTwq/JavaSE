package com.example.multithreading;

/**
 * 让一个线程等待另一个线程完成的方法——join()
 */
public class JoinThread extends Thread {

    public JoinThread(String name) {
        super(name);
    }

    /**
     * 重写run()方法，定义线程执行体
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "，" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动子线程，会和main线程并发执行
        new JoinThread("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                // 该线程不会和main线程并发执行
                JoinThread jt = new JoinThread("被join的线程");
                jt.start();
                // main线程调用了jt线程的join()方法，main线程必须等jt线程执行结束才会向下执行
                jt.join();
                System.out.println(Thread.currentThread().getName() + "，" + i);
            }
        }
    }
}
