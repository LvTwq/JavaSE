package com.example.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * ʹ��PrintStream����������װOutputStream
 *
 * @author ��ï��
 */
public class PrintStreamTest {
    public static void main(String[] args) {
        try (
                FileOutputStream fos = new FileOutputStream("test.txt");
                PrintStream ps = new PrintStream(fos)
        ) {
            // �� test.txt ��� "��ͨ�ַ���"
            ps.println("��ͨ�ַ���");
            ps.println(new PrintStreamTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
