package com.example.multithreading.concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * @author 吕茂陈
 */
public class Producer extends Thread {

    private final BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{
                "java",
                "python",
                "spring"
        };
        for (int i = 0; i < 9; i++) {
            System.out.println(getName() + "，生产者准备生产集合元素：" + bq);
            try {
                Thread.sleep(200);
                bq.put(strArr[i % 3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "，生产完成：" + bq);
        }
    }
}
