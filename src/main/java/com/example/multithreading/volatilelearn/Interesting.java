package com.example.multithreading.volatilelearn;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Interesting {

    volatile int a = 1;
    volatile int b = 1;

    public void add() {
        log.info("========开始相加=======");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        log.info("========加完了=======");
    }

    public void compare() {
        log.info("========开始比较=======");
        for (int i = 0; i < 10000; i++) {
            if (a < b) {
                log.info("a：{}，b：{}，结果：{}", a, b, a > b);
            }
        }
        log.info("=========比较完成========");
    }

    public static void main(String[] args) {
        Interesting interesting = new Interesting();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();
    }
}
