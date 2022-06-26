package com.example.multithreading.synchroniz;

/**
 * @author 吕茂陈
 * @date 2021/11/27 12:58
 */
public class B extends A {

    public static void main(String[] args) {
        methodB01();
//        B b = new B();
//        b.methodB02();
    }

    public static synchronized void methodB01() {
        System.out.println("子类的静态加锁方法，methodB01，" + Thread.currentThread().getId());

        methodA01();
    }


    private synchronized void methodB02() {
        System.out.println("子类的非静态加锁方法，methodB02，" + Thread.currentThread().getId());

        methodA01();
    }
}
