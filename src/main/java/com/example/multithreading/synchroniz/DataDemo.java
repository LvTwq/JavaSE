package com.example.multithreading.synchroniz;

import java.util.stream.IntStream;

import org.junit.Test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class DataDemo {

    @Getter
    private static volatile int counter = 0;

    private static Object locker = new Object();

    public static int rest() {
        counter = 0;
        return counter;
    }

    /**
     * 在非静态的方法上加锁，只能确保多个线程无法执行同一个实例的wrong方法
     * 不能保证不会执行不同实例的wrong方法：
     * 当 counter=1 时，两个线程同时 +1
     */
    public synchronized void wrong() {
        counter++;
    }

    public void right() {
        synchronized (DataDemo.class) {
            counter++;
        }
    }

    public static void main(String[] args) {
        DataDemo.rest();
        // 多线程循环一定次数调用Data类不同实例的wrong方法
        IntStream.rangeClosed(1, 1000000).parallel()
                .forEach(i -> new DataDemo().wrong());
        log.error("{}", DataDemo.getCounter());
    }

    @Test
    public void testRight() {
        IntStream.rangeClosed(1, 1000000).parallel()
                .forEach(i -> new DataDemo().right());
        log.info("{}", DataDemo.getCounter());
    }
}
