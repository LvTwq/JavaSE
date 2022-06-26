package com.example.multithreading.synchroniz;

/**
 * @author ��ï��
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        // ��һ������ִ��
        synchronized (lock1) {
            System.out.println("����lock1���ҽУ�" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "��lock1���н�����");
        }

        synchronized (lock1) {
            System.out.println("����lock2���ҽУ�" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "lock2���н�����");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
        }
        System.out.println("finished");
    }
}
