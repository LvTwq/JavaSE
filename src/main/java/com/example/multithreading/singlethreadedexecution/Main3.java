package com.example.multithreading.singlethreadedexecution;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author 吕茂陈
 * @date 2021/12/04 16:02
 */
public class Main3 {

    public static void main(String[] args) {
        // 设置3个资源
        BoundedResource resource = new BoundedResource(3);
        // 10个线程交替使用资源，但同时可以使用的资源最多只能是三个
        for (int i = 0; i < 10; i++) {
            new UserThread3(resource).start();
        }
    }
}

class Log {
    public static void println(String s) {
        System.out.println(Thread.currentThread().getName() + "：" + s);
    }
}

class BoundedResource {

    private final Semaphore semaphore;
    /**
     * 资源个数
     */
    private final int permits;
    private static final Random random = new Random(314159);

    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    public void use() throws InterruptedException {
        // 用于确认“是否存在可用资源”，当所有资源都被使用时，线程会阻塞在该方法中；
        // 如果线程从该方法返回，则一定存在可用资源
        semaphore.acquire();
        try {
            doUse();
        } finally {
            // 释放所有资源，必须和 acquire() 方法成对调用
            semaphore.release();
        }
    }


    protected void doUse() throws InterruptedException {
        Log.println("开始，用了" + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("结束，用了" + (permits - semaphore.availablePermits()));
    }
}

class UserThread3 extends Thread {
    private final static Random random = new Random(26535);
    private final BoundedResource resource;

    public UserThread3(BoundedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
