package com.example.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * ������һ�� object.txt�����ļ������ݾ��� Person ����
 * @author ��ï��
 */
public class WriteObject {
    public static void main(String[] args) {
        try (
                // ����һ�� ObjectOutputStream �����������������һ��������������Ҫ�����������ڵ����Ļ�����
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            Person per = new Person("�����", 600);
            // ������д�������
            oos.writeObject(per);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
