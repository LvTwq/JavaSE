package com.example.multithreading.basis;

public class StartDead extends Thread {
    private int i = 0;

    /**
     * 重写run()方法，该方法的方法体就是线程执行体
     */
    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName() + "，" + i);
        }
    }

    public static void main(String[] args) {
        StartDead sd = new StartDead();
        for (int i = 0; i < 300; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "，" + i);
            if (i == 20) {
                sd.start();
                System.out.println("线程是否存活：" + sd.isAlive());
            }
            // i>20说明该线程已经启动过了，如果sd.isAlive()为假时，则为死亡状态
            if (i > 20 && !sd.isAlive()) {
                // 死亡线程无法再次启动，否则会报 IllegalThreadStateException
                sd.start();
            }
        }
    }
}
