package com.example.generic;

/**
 * 允许在创建匿名内部类时使用菱形语法
 *
 * @author 吕茂陈
 */
public class AnnoymousTest {
    public static void main(String[] args) {
        // 指定泛型为 String
        Foo<String> f = new Foo<>() {
            @Override
            public void test(String s) {
                System.out.println("test方法的t参数为：" + s);
            }
        };
        // 使用泛型通配符，此时相当于通配符的上限为 Object
        Foo<?> fo = new Foo<>() {
            @Override
            public void test(Object o) {
                System.out.println("test方法的t参数为：" + o);
            }
        };
        // 使用泛型通配符，此时相当于通配符的上限为 Number
        Foo<? extends Number> fn = new Foo<>() {
            @Override
            public void test(Number number) {
                System.out.println("test方法的t参数为：" + number);
            }
        };
    }

    interface Foo<T> {
        void test(T t);
    }

}

