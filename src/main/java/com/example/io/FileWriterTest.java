package com.example.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ��ï��
 */
public class FileWriterTest {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("poem.txt")) {
            // \r\n ��Windowsƽ̨�Ļ��з���UNIX/Linux/BSD��ƽ̨��ʹ��\n��Ϊ���з�
            fw.write("1234\r\n");
            fw.write("1234");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
