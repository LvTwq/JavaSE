package com.example.multithreading.async;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/11 09:23
 */
@Slf4j
public class AsyncTest {

    @Test
    public void test01() {
        log.info("test01");
        async();
        async2();
        throw new RuntimeException("看看 Async 的任务有没有执行");
    }


    @Async
    public void async() {
        log.info("async");
    }

    @Async
    public void async2() {
        log.info("async2");
        try {
            throw new RuntimeException("异常了！");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
