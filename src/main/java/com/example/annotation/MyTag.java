package com.example.annotation;

/**
 * @author 吕茂陈
 */
public @interface MyTag {

    /**
     * 成员变量在注解定义中以 无形参 的方法来声明
     *
     * @return
     */
    String name() default "yeeku";

    int age() default 11;
}
