package com.example.multithreading.happensbefore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/03/08 08:58
 */
@Slf4j
public class StartExample {

    private int x = 0;
    private int y = 1;
    private boolean flag = false;

    public static void main(String[] args) {
        StartExample startExample = new StartExample();

        Thread thread1 = new Thread(startExample::writer, "线程1");
        startExample.x = 20;
        startExample.y = 20;
        startExample.flag = true;

        thread1.start();

        log.info("主线程结束");
    }

    /**
     * 线程1 看到了主线程调用start()方法前的所有赋值结果
     * 所以不会打印 0 1 false
     */
    public void writer() {
        log.info("x={}", x);
        log.info("y={}", y);
        log.info("flag={}", flag);
    }
}
