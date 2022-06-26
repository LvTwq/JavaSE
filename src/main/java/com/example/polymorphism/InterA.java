package com.example.polymorphism;

/**
 * @author 吕茂陈
 * @date 2022/03/14 08:56
 */
public interface InterA {

    default void run(){
        System.out.printf("A");
    }
}
