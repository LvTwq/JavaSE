package com.example.multithreading.basis;

public class InvokeRun extends Thread{
    private int i = 0;

    /**
     * 重写run()方法，该方法的方法体就是线程执行体
     */
    @Override
    public void run() {
        for (; i < 100; i++) {
            // Thread.currentThread().getName() 总是获取当前线程的名字，可以看到依然还是主线程
            // 直接调用run()方法时，Thread 的this.getName()返回的是该对象的名字，Thread-0 Thread-1
            System.out.println(Thread.currentThread().getName() + "，" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "，" + i);
            if (i == 20) {
                // 直接调用线程对象的run()方法，系统会把线程当作普通对象，把run()方法当成普通方法
                // 所以下面两行代码不会启动两个线程，而是依次执行两个run()方法
                new InvokeRun().run();
                new InvokeRun().run();
            }
        }
    }
}
