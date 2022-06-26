package com.example.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author ��ï��
 */
public class WriteTeacher {
    public static void main(String[] args) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
            Person per = new Person("�����", 500);
            Teacher t1 = new Teacher("��ɮ", per);
            Teacher t2 = new Teacher("������ʦ", per);
            // ���ν�4������д�������
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(per);
            oos.writeObject(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
