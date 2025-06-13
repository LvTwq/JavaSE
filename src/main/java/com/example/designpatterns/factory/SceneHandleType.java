package com.example.designpatterns.factory;

import java.lang.annotation.*;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/8/28 14:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface SceneHandleType {

    String value();
}
