package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class AnonymousTest {
    public static void main(String[] args) {
        Foo<String> f = t -> System.out.println("test方法的t参数为：" + t);
        f.test("qqq");

        // 使用通配符，此时相当于通配符的上限为Object
        Foo<?> fo = (Foo) t -> System.out.println("test方法的t参数为：" + t);

        // 使用通配符，此时相当于通配符的上限为number
        Foo<? extends Number> fn = (Foo) number -> System.out.println("test方法的number参数为：" + number);
    }
}

interface Foo<T> {
    public abstract void test(T t);
}
