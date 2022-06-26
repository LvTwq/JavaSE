package com.example.polymorphism;

public class BaseClass {
    public int book = 6;

    private static int i1 = 1;

    public void base() {
        System.out.println("父类的普通方法");
    }

    public void test() {
        System.out.println("父类的被覆盖的方法");
    }

    public static void testStatic(){
        System.out.println("父类的静态方法");
    }


    void test01(){
        System.out.println("看看包的符号");
    }
}
