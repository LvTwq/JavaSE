package com.example.optional;

import java.util.Optional;

/**
 * @author 吕茂陈
 */
public class Test {

    public static void main(String[] args) {
        // 通过of()方法创建实例
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        // 没传任何参数，输出的都是默认值
        System.out.println("emp");
    }
}

