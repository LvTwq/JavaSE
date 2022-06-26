package com.example.multithreading;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    /**
     * 创建新线程，线程名为name，属于group线程组
     *
     * @param group
     * @param name
     */
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + "，线程的i变量" + i);
        }
    }
}
