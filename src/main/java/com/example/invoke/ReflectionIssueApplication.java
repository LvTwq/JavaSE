package com.example.invoke;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 吕茂陈
 * @date 2022-07-04 16:53
 */
@Slf4j
public class ReflectionIssueApplication {
    private void age(int age) {
        log.info("int age = {}", age);
    }

    private void age(Integer age) {
        log.info("Integer age = {}", age);
    }


    /**
     * 反射调用方法，是以反射获取方法时传入的方法名称和参数类型来确定调用方法的
     * 而不是入参传值
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Integer.TYPE 代表的是 int，无论传入的是 Integer.valueOf(“22”) 还是基本类型的 22，会调用 第一个 age 方法
        getClass().getDeclaredMethod("age", Integer.TYPE).invoke(this, Integer.valueOf("22"));
        // 调用第二个age方法
        getClass().getDeclaredMethod("age", Integer.class).invoke(this, 22);
    }
}
