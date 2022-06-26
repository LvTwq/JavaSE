package com.example.multithreading.basis;

/**
 * @author 吕茂陈
 */
public class SecondThread implements Runnable {


    private int i = 0;

    /**
     * 实现Runnable接口来创建多线程
     * 一、定义Runnable接口的实现类，并重写该接口的run()方法，该方法的方法体就是线程执行体
     * 二、创建Runnable实现类的实例，并以此实例作为Thread的target来创建Thread对象，该对象是线程对象
     */
    @Override
    public void run() {
        for (; i < 100; i++) {
            // 当线程类实现Runnable接口时，如果想要获取当前线程，只能用 Thread.currentThread().getName()
            System.out.println(Thread.currentThread().getName() + "，" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "，" + i);
            if (i == 20) {
                SecondThread st = new SecondThread();
                // 通过 new Thread(target,name) 的方法创建新线程
                new Thread(st, "新线程1").start();
                new Thread(st, "新线程2").start();
            }
        }
    }
}
