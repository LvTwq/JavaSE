package com.example.multithreading.basis;

public class PriorityTest extends Thread {

    public PriorityTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + "，其优先级是：" + getPriority() + "，循环变量值为：" + i);
        }
    }

    public static void main(String[] args) {
        // 主线程优先级为6，这样由main线程创建的子线程的优先级默认是6
        Thread.currentThread().setPriority(6);
        for (int i = 0; i < 30; i++) {
            if (i == 10) {
                PriorityTest low = new PriorityTest("低级");
                low.start();
                System.out.println("低级创建之初的优先级：" + low.getPriority());
                // 设置该线程为低优先级
                low.setPriority(Thread.MIN_PRIORITY);
            }
            if (i == 20) {
                PriorityTest high = new PriorityTest("高级");
                high.start();
                System.out.println("高级创建之初的优先级：" + high.getPriority());
                // 设置该线程为高优先级
                high.setPriority(Thread.MAX_PRIORITY);
            }
        }
    }
}
