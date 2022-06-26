package com.example.中文;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class 中文测试类 {
    public static void main(String[] args) {
        int 变量 = 10;
        变量++;
        System.out.println(变量);

        中文 字符串 = 私有方法(变量);
        System.out.println(字符串);
    }

    private static 中文 私有方法(int 变量) {
        return new 中文("中文字符串");
    }
}

@ToString
@AllArgsConstructor
class 中文 {
    private String 中文;
}
