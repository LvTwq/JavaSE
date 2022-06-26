package com.example.multithreading;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 */
public class Data {

    @Getter
    private static int counter = 0;

    private static Object locker = new Object();

    public static int rest() {
        counter = 0;
        return counter;
    }

    public synchronized void wrong() {
        counter++;
    }

    public void right() {
        synchronized (locker) {
            counter++;
        }
    }

    public static void main(String[] args) {
        Data.rest();
        // 多线程循环一定次数调用Data类不同实例的wrong方法
        IntStream.rangeClosed(1, 1000000).parallel()
                .forEach(i -> new Data().wrong());
        System.out.println(Data.getCounter());

        IntStream.rangeClosed(1, 1000000).parallel()
                .forEach(i -> new Data().right());
        System.out.println(Data.getCounter());
    }
}
