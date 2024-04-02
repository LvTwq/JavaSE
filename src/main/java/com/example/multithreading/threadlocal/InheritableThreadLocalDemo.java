package com.example.multithreading.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/3 16:37
 */
@Slf4j
public class InheritableThreadLocalDemo {

    /**
     * InheritableThreadLocal 的核心思想即：让我们可以在父线程创建子线程的时候将 ThreadLocal 中的值传递给子线程
     */
    private static final InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        Integer reqId = 5;
        InheritableThreadLocalDemo threadLocalExample = new InheritableThreadLocalDemo();
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
                log.info("子线程启动");
                log.info("在子线程中访问requestId:{}", requestIdThreadLocal.get());
            }
        })).start();
    }
}
