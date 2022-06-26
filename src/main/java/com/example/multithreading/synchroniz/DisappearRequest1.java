package com.example.multithreading.synchroniz;

/**
 * @author ��ï��
 * �����߳�ִ������������Ϊ 20000
 */
public class DisappearRequest1 implements Runnable {

    static DisappearRequest1 instance = new DisappearRequest1();

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        // �������߳��õ���ͬһ��ʵ�������Թ������ʵ����ķ���
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        // Ϊ�˱�֤�����߳�ִ�����������������̵߳ȴ�
        t1.join();
        t2.join();
        System.out.println(i);

    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }
}
