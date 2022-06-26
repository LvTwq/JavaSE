package com.example.multithreading.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author 吕茂陈
 */
public class BlockingQueueTest {

    @Test
    public void test01() throws InterruptedException {
        // 定义一个长度为2的阻塞队列
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        // 向队列尾部插入元素，队列已满会阻塞
        bq.put("Java");
        bq.put("Java");
        bq.put("Java");
    }

    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
        // 3个生产者线程都往bq中放入元素，但只要其中一个线程向该队列中放入元素之后，其他生产者线程就必须等待消费者线程取出
        new Producer(bq).start();
        new Producer(bq).start();
        new Producer(bq).start();
        new Consumer(bq).start();
    }
}
