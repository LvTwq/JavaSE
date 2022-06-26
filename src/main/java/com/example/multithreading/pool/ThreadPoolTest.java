package com.example.multithreading.pool;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 为什么要使用线程池？
 *      启动一个新线程成本较高，因为涉及到与操作系统的交互，为了提高性能，尤其当程序中需要创建大量空闲线程时，更应该考虑使用线程池
 *      线程池在系统启动时就会创建大量空闲线程，程序将一个Runnable对象或Callable对象传给线程池，线程池就会启动一个空闲线程来执行他们的run()或call()方法，
 *      执行结束后，线程不会死亡，而是再次返回线程池中成为空闲状态，等待执行下一个Runnable对象的run()或call()方法
 *      除此之外，使用线程池可以控制系统中的并发线程的数量
 * @author 吕茂陈
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 创建一个具有固定线程数（6）的线程池
        ExecutorService pool = newFixedThreadPool(6);
        // 创建线程任务
        Runnable target = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "，i的值为：" + i);
            }
        };
        // 线程池将在有空闲线程时执行 Runnable 对象
        pool.submit(target);
        pool.submit(target);
        pool.shutdown();
    }
}
