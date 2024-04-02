package com.example.serializable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
public class ReadObject {


	public static void main(String[] args) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"))) {
			Person p = (Person) ois.readObject();
			log.info("{}", p.getName() + p.getAge());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void main() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {
			Teacher t1 = (Teacher) ois.readObject();
			Teacher t2 = (Teacher) ois.readObject();
			Person p = (Person) ois.readObject();
			Teacher t3 = (Teacher) ois.readObject();
			log.info("{}", t1.getStudent() == p);
			log.info("{}", t2.getStudent() == p);
			log.info("{}", t2 == t3);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}




}
