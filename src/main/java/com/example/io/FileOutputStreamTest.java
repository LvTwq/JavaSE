package com.example.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ��ï��
 */
public class FileOutputStreamTest {
    public static void main(String[] args) {
        try (
                // �����ֽ�������
                FileInputStream fis = new FileInputStream("src/main/java/org/example/inputandoutput/FileOutputStreamTest.java");
                // �����ֽ������
                FileOutputStream fos = new FileOutputStream("newFile.txt")) {
            byte[] bbuf = new byte[32];
            int hasRead = 0;
            while ((hasRead = fis.read(bbuf)) > 0) {
                // �� bbuf �����0��ʼ������ΪhasRead���ֽ�д�뵽�������
                fos.write(bbuf, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
