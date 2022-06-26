package com.example.file;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 吕茂陈
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        // 创建字节输入流
        try (FileInputStream fis = new FileInputStream("src/main/java/org/example/inputandoutput/FileInputStreamTest.java")) {
            // 创建一个长度为1024字节的“竹筒”
            byte[] bbuf = new byte[1024];
            // 保存实际读取的字节数
            int hasRead;
            // 从输入流中读取单个字节，返回的是所读取地单个字节数据（字节数据可以直接转换为int类型）
            while ((hasRead = fis.read(bbuf)) > 0) {
                // 取出“竹筒”中的水滴（字节），将字节转换成字符串输出
                System.out.println(new String(bbuf, 0, hasRead));
            }
        }
    }
}
