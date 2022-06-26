package com.example.proxy;

/**
 * 这两个方法要被插入到 info() 和 run() 当中
 * @author 吕茂陈
 */
public class DogUtil {

    public void method1() {
        System.out.println("=====模拟第一个通用方法======");
    }

    public void method2() {
        System.out.println("=====模拟第二个通用方法======");
    }
}
