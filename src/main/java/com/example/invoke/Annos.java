package com.example.invoke;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 吕茂陈
 */
@Repeatable(Annos.class)
@interface Anno{}

/**
 * @author 吕茂陈
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Annos {
    Anno[] value();
}
