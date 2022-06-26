package com.example.optional;

import java.util.Optional;

import org.junit.Test;

/**
 * @author 吕茂陈
 */
public class TestOptional {

    @Test
    public void test1() {
        // 通过of()方法创建实例
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        // 没传任何参数，输出的都是默认值
        System.out.println(emp);
    }

    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(null);
        Employee employee = op.orElseGet(Employee::new);
        System.out.println(employee);
    }

    @Test
    public void test4() {
        Optional<Employee> op =
                Optional.ofNullable(Employee.builder().age(10).name("1111").salary(11.0).build());
        // 把容器中的对象应用到map()上
        Optional<String> s = op.map(Employee::getName);
        System.out.println(s);

        Optional<String> s1 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(s1);
    }


}
