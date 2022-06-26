package com.example.training;

/**
 * 给定一个整数，将其转化为7进制，并以字符串的形式输出
 *
 * @author 吕茂陈
 */
public class ConvertToBase7 {
    public static void main(String[] args) {
        System.out.println(convert(-100));
    }

    public static String convert(int num) {
        String yushu = "";
        String fh = "";

        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            fh = "-";
            num = -num;
        }
        while (num > 0) {
            yushu = num % 7 + yushu;
            num /= 7;
        }
        return fh + yushu;
    }
}
