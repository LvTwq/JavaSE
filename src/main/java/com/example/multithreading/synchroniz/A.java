package com.example.multithreading.synchroniz;

/**
 * @author 吕茂陈
 * @date 2021/11/27 12:57
 */
public class A {

    public static synchronized void methodA01() {
        System.out.println("父类的静态加锁方法，methodA01，" + Thread.currentThread().getId());
    }
}
