package com.example.multithreading.basis;

/**
 * @author 吕茂陈
 * @date 2021/11/25 18:32
 */
public class ThreadTest implements Runnable {
    private int flag = 1;

    /**
     * 两个static的对象，静态变量
     */
    public static Object obj1 = new Object();
    public static Object obj2 = new Object();

    public ThreadTest(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            fun1();
        }
        if (flag == 0) {
            fun2();
        }
    }

    private void fun1() {
        synchronized (obj1) {
            System.out.println("我已经锁定了obj1,休息0.5秒后再锁定obj2,但是估计进不了obj2，因为obj2估计也被锁定了");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("进入了obj2");
            }
        }
    }

    private void fun2() {
        synchronized (obj2) {
            System.out.println("我已经锁定了obj2,休息0.5秒后再锁定obj1,但是估计进不了obj1，因为obj1估计也被锁定了");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("进入了obj1");
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest deadLock1 = new ThreadTest(1);
        ThreadTest deadLock2 = new ThreadTest(0);
        System.out.println("线程开始了");
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();

    }
}
