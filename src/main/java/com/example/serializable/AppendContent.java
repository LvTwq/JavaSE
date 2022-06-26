package com.example.serializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author ��ï��
 */
public class AppendContent {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("out.txt", "rw")) {
            // ����¼ָ���ƶ��� out.txt �ļ����
            raf.seek(raf.length());
            raf.write("׷�ӵ����ݣ�\r\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
