package com.example.oop;

import org.junit.Test;

/**
 * @author Administrator
 */
public class Dog {


    public void jump(){
        System.out.println("正在执行jump()方法" );
    }

    @Test
    public void run(){
        jump();
    }
}
