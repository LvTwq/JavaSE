package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * “容器”注解的保留期必须比他所包含的注解的保留期更长
 *
 * @author 吕茂陈
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FkTags {

    /**
     * 该成员变量可以接收多个 @FkTag 注解
     *
     * @return
     */
    FkTag[] value();

}
