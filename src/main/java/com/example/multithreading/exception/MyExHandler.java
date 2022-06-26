package com.example.multithreading.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/05/12 09:31
 */
@Slf4j
public class MyExHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 处理线程的未处理异常
     *
     * @param t 出现异常的线程
     * @param e 该线程抛出的异常
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("{}，线程出现异常：", t, e);
    }
}

