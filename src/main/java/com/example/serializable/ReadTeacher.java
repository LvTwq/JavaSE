package com.example.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author ��ï��
 */
public class ReadTeacher {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {
            // ���ζ�ȡObjectInputStream�������е�4������
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println("t1��student���ú�p�Ƿ���ͬ" + (t1.getStudent() == p));
            System.out.println("t2��student���ú�p�Ƿ���ͬ" + (t2.getStudent() == p));
            System.out.println("t2��t3�Ƿ�ͬһ������" + (t2 == t3));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
