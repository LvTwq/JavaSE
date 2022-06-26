package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记注解（不包含任何成员变量）、注解保留到运行时、用于修饰方法
 * <p>
 * 用于标记哪些方法是可测试的
 *
 * @author 吕茂陈
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Testable {
}
