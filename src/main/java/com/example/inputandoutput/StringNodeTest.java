package com.example.inputandoutput;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author 吕茂陈
 */
public class StringNodeTest {
    public static void main(String[] args) {
        String src = "我和我骄傲的倔强\n"
                + "握紧双手绝对不放\n"
                + "这一次\n";
        char[] buffer = new char[32];
        int hasRead;

        try (
                // 传入的是字符串节点，而不是文件节点
                StringReader sr = new StringReader(src)
        ) {
            // 循环读取字符串
            while ((hasRead = sr.read(buffer)) > 0) {
                System.out.println(new String(buffer, 0, hasRead));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (
                // 创建 StringWriter 时，实际上以一个StringBuffer 作为输出节点
                // 20就是StringWriter的初始长度
                StringWriter sw = new StringWriter(20)
        ) {
            // 将字符串里的包含的字符输出到指定输出流中
            sw.write("当我和世界不一样\n");
            sw.write("那就让我不一样\n");
            sw.write("我就是我自己的神\n");
            System.out.println("---下面是sw字符串节点里的内容---");
            // 返回 StringWriter 字符串节点的内容
            System.out.println(sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
