package com.example.basis;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/4/13 14:34
 */
@Slf4j
public class StaticTest {

    static int count = 10;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            func1();
        }
    }


    static void func1() {
        /* 'thingy' 是 'func1' 的局部变量 - 只初始化一次
         * 每次调用函数 'func1' 'thingy' 值不会被重置。
         */
        int thingy = 5;
        thingy++;
        log.info("thingy : {},count : {}", thingy, count);
    }
}
