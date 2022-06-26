package com.example.optional;

import java.util.Optional;

/**
 * @author 吕茂陈
 */
public class Java8Tester {
    public static void main(String[] args) {
        Java8Tester java8Tester = new Java8Tester();

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(null);
        System.out.println("a = " + a);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(10);
        System.out.println(java8Tester.sum(a, b));

    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在：" + a.isPresent());
        System.out.println("第二个参数值存在：" + b.isPresent());

        // Optional.orElse - 如果值存在，返回该值，否则返回指定的默认值
        Integer value1 = a.orElse(8);
        System.out.println("value1:" + value1);

        // Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        System.out.println("value2:" + value2);


        return value1 + value2;
    }
}
