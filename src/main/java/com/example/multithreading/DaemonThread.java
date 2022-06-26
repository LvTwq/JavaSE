package com.example.multithreading;

/**
 * 如果所有的前台线程都死亡，后台线程自动死亡
 *
 * @author 吕茂陈
 */
public class DaemonThread extends Thread {

    public DaemonThread(String name) {
        super(name);
    }

    /**
     * 重写run()方法，定义线程执行体
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "，" + i);
        }
    }

    public static void main(String[] args) {
        DaemonThread t = new DaemonThread("后台线程");
        // 将此线程设置成后台线程，然后再启动，顺序不能错
        t.setDaemon(true);
        t.start();
        // 当i=10，main线程死亡，jvm会通知后台线程，后台线程从接受指令到做出响应，需要一定时间
        for (int i = 0; i < 10; i++) {
            System.out.println("=====" + Thread.currentThread().getName() + "，" + i);
        }
    }
}
