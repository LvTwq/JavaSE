package com.example.annotation;

/**
 * @author 吕茂陈
 */
public class InheritableTest extends Base {

    public static void main(String[] args) {
        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
    }
}
