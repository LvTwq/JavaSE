package com.example.generic;

/**
 * @author 吕茂陈
 */
public class Circle extends Shape {
    @Override
    public void draw(Canvas c) {
        System.out.println("在画布"+c +"上画一个圆");
    }
}
