package com.example.multithreading.basis;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 吕茂陈
 */
public class ThirdThread {

    public static void main(String[] args) {
        // 使用lambda表达式创建 Callable<Integer> （接口）对象，就无须创建Callable实现类，再创建Callable对象
        // 使用FutureTask包装Callable对象
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "的循环变量 i 的值：" + i);
            }
            // call()方法可以有返回值
            return i;
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " 的循环变量 i 的值：" + i);
            if (i == 20) {
                // 启动以FutureTask对象为target的线程（实质还是以Callable对象来创建并启动线程的）
                new Thread(task, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
