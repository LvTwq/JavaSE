package com.example.proxy;

/**
 * 要求：程序执行这两个方法时，能调用某个通用方法，但又不是通过硬编码方式调用该方法
 */
public interface Dog {

    /**
     * 代码段1
     */
    void info();

    /**
     * 代码段2
     */
    void run();
}
