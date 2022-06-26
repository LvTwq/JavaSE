package com.example.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 程序不再使用键盘作为标准输入，而是使用 RedirectIn.java 作为标准输入源
 * @author 吕茂陈
 */
public class RedirectIn {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("src/main/java/org/example/inputandoutput/RedirectIn.java")) {
            // 将标准输入重定向到fis输入流
            System.setIn(fis);
            // 获取标准输入
            Scanner sc = new Scanner(System.in);
            // 只把回车当作分隔符
            sc.useDelimiter("\n");
            // 判断是否还有下一个输入项
            while (sc.hasNext()) {
                System.out.println("键盘输入的内容是：" + sc.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
