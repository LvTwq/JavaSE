package com.example.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java 使用 System.in 代表标准输入，即键盘输入，但是InputStream类的实例，使用不方便键盘输入的内容都是文本内容，可以转成字符输入流
 * @author 吕茂陈
 */
public class KeyinTest {
    public static void main(String[] args) {

        try (
                // 将 System.in 对象转换为 字符输入流
                InputStreamReader reader = new InputStreamReader(System.in);
                // 字符输入流包装成 BufferedReader（具有缓冲功能，可以一次读取一行文本——以换行符为标志）
                BufferedReader br = new BufferedReader(reader)) {
            String line;
            // readLine() 一次读取一行内容
            while ((line = br.readLine()) != null) {
                if (line.equals("exit")) {
                    System.exit(1);
                }
                System.out.println("输入的内容为：" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
