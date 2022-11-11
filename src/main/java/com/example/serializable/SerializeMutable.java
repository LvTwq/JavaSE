package com.example.serializable;

import java.io.*;

/**
 * @author ��ï��
 */
public class SerializeMutable {
	public static void main(String[] args) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutable.txt"));
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mutable.txt"))) {
			Person per = new Person("�����", 600);
			// ��per����ת���ֽ����в����
			oos.writeObject(per);
			// �ı�per�����nameʵ��������ֵ
			per.setName("��˽�");
			// ϵͳֻ����������л���ţ����Ըı���name���ᱻ���л�
			oos.writeObject(per);
			Person p1 = (Person) ois.readObject();
			Person p2 = (Person) ois.readObject();
			System.out.println(p1 == p1);
			// ��Ȼ���������ա���֤���ı���ʵ������û�б����л�
			System.out.println(p2.getName());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
