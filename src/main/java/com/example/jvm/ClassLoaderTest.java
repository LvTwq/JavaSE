package com.example.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/6 10:28
 */
@Slf4j
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        log.info("loader:{}", loader);
        log.info("parent:{}", loader.getParent());
        log.info("parent.parent:{}", loader.getParent().getParent());
    }
}
