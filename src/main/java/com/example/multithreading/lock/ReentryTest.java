package com.example.multithreading.lock;

/**
 * synchronized 是可重入锁：允许一个线程二次请求自己持有对象锁的临界资源
 *
 * @author 吕茂陈
 */
public class ReentryTest extends AT {

    public static void main(String[] args) {
//        ReentryTest reentry = new ReentryTest();
//        reentry.doA();
        doB();
    }

//    @Override
//    public synchronized void doA() {
//        System.out.println("子类方法：ReentryTest.doA() ThreadId：" + Thread.currentThread().getId());
//        doB();
//    }

    public static synchronized void doB() {
        doA();
        System.out.println("子类方法：ReentryTest.doB() ThreadId：" + Thread.currentThread().getId());
    }

}


class AT {
    public static synchronized void doA() {
        System.out.println("父类方法：A.doA() ThreadId：" + Thread.currentThread().getId());
    }
}

