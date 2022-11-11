package com.example.serializable;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author ��ï��
 */
public class WriteTeacher {
	public static void main(String[] args) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
			Person per = new Person("456", 500);
			Teacher t1 = new Teacher("123", per);
			Teacher t2 = new Teacher("963", per);
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(per);
			oos.writeObject(t2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void main() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
			Person per = new Person("123", 600);
			oos.writeObject(per);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
