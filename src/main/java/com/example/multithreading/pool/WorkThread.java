package com.example.multithreading.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * @author 吕茂陈
 * @date 2022/02/19 16:50
 */
@Slf4j
public class WorkThread extends Thread {

    /**
     * 工作队列，由所有工作者线程共享
     */
    private final BlockingQueue<Runnable> queue;

    public WorkThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 对该队列进行并发访问时，需要采用某种同步机制
                Runnable take = queue.take();
                take.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                // 允许线程退出
                break;
            }

        }
    }
}
