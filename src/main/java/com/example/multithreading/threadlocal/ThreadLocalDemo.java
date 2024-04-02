package com.example.multithreading.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/3 16:30
 */
@Slf4j
public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Integer reqId = 5;
        ThreadLocalDemo threadLocalExample = new ThreadLocalDemo();
        threadLocalExample.setRequestId(reqId);
    }

    public void setRequestId(Integer requestId) {
        requestIdThreadLocal.set(requestId);
        doBussiness();
    }

    public void doBussiness() {
        log.info("首先打印requestId:{}", requestIdThreadLocal.get());
        (new Thread(new Runnable() {
            @Override
            public void run() {
                // 在子线程中无法访问在父线程中设置的本地线程变量，因为子线程拥有自己的 ThreadLocalMap，无法获取父线程 ThreadLocalMap 中的值
                log.info("子线程启动");
                log.info("在子线程中访问requestId:{}", requestIdThreadLocal.get());
            }
        })).start();
    }
}
