package com.example.multithreading.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ExHandler {
    public static void main(String[] args) {
        // 设置主线程的异常处理器
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());
        int a = 5 / 0;
        log.info("{}", "程序正常结束！");
    }
}

