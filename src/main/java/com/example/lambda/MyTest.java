package com.example.lambda;

import javax.swing.*;

/**
 * @author 吕茂陈
 */
@FunctionalInterface
interface MyTest {
    String test(String a, int b, int c);
}

@FunctionalInterface
interface YourTest {
    JFrame win(String title);
}

@FunctionalInterface
interface Displayable {
    void display();

    default int add(int a, int b) {
        return a + b;
    }
}
