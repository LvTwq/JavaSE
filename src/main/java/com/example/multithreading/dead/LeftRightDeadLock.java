package com.example.multithreading.dead;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/16 21:27
 */
@Slf4j
public class LeftRightDeadLock {

    private final Object left = new Object();

    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                log.info("先左后右");
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                log.info("先右后左");
            }
        }
    }

    public static void main(String[] args) {
        LeftRightDeadLock deadLock = new LeftRightDeadLock();
        new Thread(() -> {
            deadLock.leftRight();
            deadLock.rightLeft();
        }).start();
    }
}
