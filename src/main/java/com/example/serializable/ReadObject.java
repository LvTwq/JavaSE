package com.example.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ReadObject {
    public static void main(String[] args) {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.txt"))) {
            Person p = (Person) ois.readObject();
            System.out.println(p.getName() + p.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
