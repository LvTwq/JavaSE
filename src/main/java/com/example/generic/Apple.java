package com.example.generic;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author 吕茂陈
 */
@Data
@AllArgsConstructor
public class Apple<T> {

    private T info;

    public static void main(String[] args) {
        // 其构造器依然是Apple，而不是Apple<T>
        Apple<String> a1 = new Apple<>("苹果");
        System.out.println(a1.getInfo());

        Apple<Double> a2 = new Apple<>(5.67);
        System.out.println(a2.getInfo());

    }
}
