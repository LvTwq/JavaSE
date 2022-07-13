package com.example.serializable;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author ��ï��
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("object.txt", "r")) {
            System.out.println("RandomAccessFile���ļ�ָ��ĳ�ʼλ�ã�" + raf.getFilePointer());
            raf.seek(300);
            byte[] bbuf = new byte[1024];
            int hasRead = 0;
            while ((hasRead = raf.read(bbuf)) > 0) {
                System.out.println(new String(bbuf, 0, hasRead));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
