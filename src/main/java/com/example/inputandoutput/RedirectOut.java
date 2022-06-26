package com.example.inputandoutput;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 标准输出流是输出到屏幕，通过 PrintStream 重定向到文件
 *
 * @author 吕茂陈
 */
public class RedirectOut {
    public static void main(String[] args) {
        try (
                // 创建PrintStream输出流
                PrintStream ps = new PrintStream(new FileOutputStream("out.txt"))) {
            // 将标准输出重定向到ps输出流
            System.setOut(ps);
            // 向标准输出输出一个字符串
            System.out.println("普通字符串");
            // 向标准输出输出一个对象
            System.out.println(new RedirectOut());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
