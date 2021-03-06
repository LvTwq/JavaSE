package com.example.multithreading.synchroniz;

/**
 * @author ��ï��
 */
public class SynchronizedObjectMethod3 implements Runnable{

    static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();


    @Override
    public void run() {
        method();
    }

    public synchronized void method(){
        System.out.println("���Ƕ������ķ������η���ʽ���ҽУ�" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "���н�����");
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
