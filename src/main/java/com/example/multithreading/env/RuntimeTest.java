package com.example.multithreading.env;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/12 15:35
 */
@Slf4j
public class RuntimeTest {

    @Test
    public void test01() {
        int cpus = Runtime.getRuntime().availableProcessors();
        log.info("CPU 数目：{}", cpus);
    }
}
