package com.example.multithreading;

/**
 * 继承Thread即可创建线程类
 *
 * @author 吕茂陈
 */
public class FirstThread extends Thread {

    private int i = 0;

    /**
     * 重写run()方法，该方法的方法体就是线程执行体
     */
    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "，" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "，" + i);
            if (i == 20) {
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }
}
