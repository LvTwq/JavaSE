package com.example.multithreading.happensbefore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/03/08 09:07
 */
@Slf4j
public class JoinExample {
    private int x = 0;
    private int y = 1;
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        JoinExample joinExample = new JoinExample();

        Thread thread1 = new Thread(joinExample::writer, "线程1");
        thread1.start();

        thread1.join();

        log.info("x={}", joinExample.x);
        log.info("y={}", joinExample.y);
        log.info("flag={}", joinExample.flag);
        log.info("主线程结束");

    }

    public void writer() {
        this.x = 100;
        this.y = 200;
        this.flag = true;
    }
}
