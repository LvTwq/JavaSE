package com.example.multithreading.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * @author 吕茂陈
 */
public class Consumer extends Thread {

    private final BlockingQueue<String> bq;

    public Consumer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println(getName() + "，消费者第" + ++i + "次准备消费集合元素：" + bq);
            try {
                Thread.sleep(200);
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "，第" + ++i + "次消费完成：" + bq);
        }
    }
}
