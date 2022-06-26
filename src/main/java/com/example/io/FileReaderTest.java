package com.example.io;

import java.io.FileReader;
import java.io.IOException;


/**
 * @author 吕茂陈
 */
public class FileReaderTest {
    public static void main(String[] args) {
        try (
                FileReader fr = new FileReader("src/main/java/org/example/inputandoutput/FileReaderTest.java")) {
            // 创建一个长度为32的 竹筒
            char[] cbuf = new char[32];
            // 保存实际读取的字节数
            int hasRead = 0;
            while ((hasRead = fr.read(cbuf)) > 0) {
                // 将字符数组转换成字符串输入
                System.out.println(new String(cbuf, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
