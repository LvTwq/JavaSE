package com.example.annotation;


import java.lang.annotation.Annotation;

/**
 * @author 吕茂陈
 */

@FkTag(value=5)
@FkTag(value=6)
public class RepeatableTest {

    @MyTag(name = "小明", age = 23)
    public void info() {
        System.out.println("我是小明");
    }

    public static void main(String[] args) throws Exception {
        Annotation[] aArray = Class.forName("com.example.annotation.RepeatableTest").getMethod("info").getAnnotations();
        for (Annotation an : aArray) {
            System.out.println(an);
        }
    }
}
