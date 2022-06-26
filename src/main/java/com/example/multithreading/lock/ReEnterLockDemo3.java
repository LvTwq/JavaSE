package com.example.multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层依然可以使用，并且不发生死锁
 * <p>
 * 在一个synchronized修饰的方法或代码块内部，调用本类其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 *
 * @author 吕茂陈
 * @date 2021/10/11 09:11
 */
public class ReEnterLockDemo3 {

    static Lock lock = new ReentrantLock();

    public static void m1() {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("======外层");
                lock.lock();
                try {
                    System.out.println("======内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }

    public static void main(String[] args) {
        m1();
    }
}
