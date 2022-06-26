package com.example.annotation;

/**
 * @author 吕茂陈
 */
public class MyTest {

    @Testable
    public static void m1() {
    }

    public static void m2() {
    }

    @Testable
    public static void m3() {
        throw new IllegalArgumentException("参数异常！！！");
    }

    public static void m4() {
    }

    @Testable
    public static void m5() {
    }

    public static void m6() {
    }

    @Testable
    public static void m7() {
        throw new RuntimeException("业务异常！！！");
    }

    public static void m8() {
    }
}
