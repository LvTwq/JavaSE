package com.example.multithreading.reenterlock;

/**
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层依然可以使用，并且不发生死锁
 * <p>
 * 在一个synchronized修饰的方法或代码块内部，调用本类其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 *
 * @author 吕茂陈
 * @date 2021/10/11 09:11
 */
public class ReEnterLockDemo {

    static Object objectLockA = new Object();
    static Object objectLockB = new Object();

    public static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "外层调用！");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "中层调用！");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "内层调用！");
                    }
                }
            }
        }, "t1").start();
    }

    public static void main(String[] args) {
        m1();
    }
}
