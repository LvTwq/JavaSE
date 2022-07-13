package com.example.invoke;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author 吕茂陈
 * @date 2022-07-05 17:06
 */
@Slf4j
public class Main {

    @Test
    public void test01() {
        Child1 child1 = new Child1();
        /*
         getMethods 方法能获得当前类和父类的所有 public 方法
         父类的 setValue 会执行两次，子类的会执行一次
         */
        Arrays.stream(child1.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        log.info(child1.toString());
    }

    @Test
    public void test02() {
        Child1 child1 = new Child1();
        /*
         getDeclaredMethods 只能获得当前类所有的 public、protected、package 和 private 方法
         父子类的都只会执行一次
         */
        Arrays.stream(child1.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        log.info(child1.toString());
    }

    @Test
    public void test03() {
        Child2 child2 = new Child2();
        /*
         泛型擦除后，child2 有两个 setValue 方法，入参分别是 String 和 Object
         */
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(method -> {
                    try {
                        method.invoke(child2, "test");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        log.info(child2.toString());
    }

    @Test
    public void test04() {
        Child2 child2 = new Child2();
        /*
         泛型擦除后，child2 有两个 setValue 方法，入参分别是 String 和 Object
         */
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .forEach(method -> {
                    try {
                        method.invoke(child2, "test");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
        log.info(child2.toString());
    }
}