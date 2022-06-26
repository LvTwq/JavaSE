package com.example.invoke;

import org.junit.Test;

public class Tester {
    static {
        System.out.println("Tester类的静态初始化块。。。");
    }

    @Test
    public void testStatic() throws ClassNotFoundException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        // 仅加载Tester类
        cl.loadClass("com.example.invoke.Tester");
        System.out.println("系统加载Tester类");
        // 初始化Tester类
        Class.forName("com.example.invoke.Tester");
    }
}
