package com.example.multithreading;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 两个线程共用一个实例，两个线程两个实例？
 *      这个写法只会有一个实例
 *
 * @author 吕茂陈
 * @date 2021/10/19 08:57
 */
@Slf4j
@AllArgsConstructor
public class BlankThreadTest {

    public static void main(String[] args) {
        Blank blank = new Blank("账户1", 1000);

        Thread thread01 = new Thread(new Runnable() {
            @Override
            public void run() {
                int leftover = blank.withdraw(1000);
                log.info("thread01，取1000成功！！");
                log.info("剩下{}", leftover);
            }
        }, "thread01");
        Thread thread02 = new Thread(new Runnable() {
            @Override
            public void run() {
                int leftover = blank.withdraw(500);
                log.info("thread02，取500成功！！");
                log.info("剩下{}", leftover);
            }
        }, "thread02");


        thread01.start();
        thread02.start();
    }

}
