package com.example.initialization;

public class MethodInit3 {

    // 注意初始化顺序
//    int j = g(i);

    int i = f();

    private int f() {
        return 0;
    }

    int g(int n) {
        return n * 10;
    }
}
