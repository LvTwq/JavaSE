package com.example.multithreading.lock;


/**
 * 当两个线程相互等待对方释放释放同步监视器时就会发生死锁，
 * 一旦出现死锁，整个程序不会发生任何异常，也不会给出任何提示，只是所有线程处于阻塞状态，无法继续
 *
 * @author 吕茂陈
 */
public class DeadLock implements Runnable {

    A a = new A();
    B b = new B();

    public void init() {
        // 主线程的线程执行体是 main() 方法
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入主线程之后");
    }

    @Override
    public void run() {
        // 副线程的线程执行体是 run() 方法
        Thread.currentThread().setName("副线程");
        b.bar(a);
        System.out.println("进入副线程之后");
    }

    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        // 以dl为target启动新线程
        new Thread(dl).start();
        dl.init();
    }
}

class A {
    public synchronized void foo(B b) {
        System.out.println("当前线程名：" + Thread.currentThread().getName()
                + "，进入了A实例的foo()方法");
        try {
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() + "休眠");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名：" + Thread.currentThread().getName()
                + "，醒过来，企图调用B实例的last()方法");
        b.last();
    }

    public synchronized void last() {
        System.out.println("进入了A类的last()方法内部");
    }
}

class B {

    public synchronized void bar(A a) {
        System.out.println("当前线程名：" + Thread.currentThread().getName() + "，进入了B实例的bar()方法");
        try {
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() + "休眠");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名：" + Thread.currentThread().getName() + "，醒过来，企图调用A实例的last()方法");
        a.last();
    }

    public synchronized void last() {
        System.out.println("进入了B类的last()方法内部");
    }
}
