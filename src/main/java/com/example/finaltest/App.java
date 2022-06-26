package com.example.finaltest;

/**
 * Hello world!
 *
 * @author 吕茂陈
 */
public class App {

    private static String test = "1234";

    public static void main(String[] args) {
        test01();
        System.out.println(test);
        test02();
    }

    private static void test02() {
        System.out.println("test02:" + test);
    }

    private static void test01() {
        System.out.println(test);
        test = "asdfg";
        System.out.println("test01:" + test);
    }
}
