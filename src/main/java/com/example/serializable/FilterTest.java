package com.example.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class FilterTest {
    public static void main(String[] args) {
        try (
                InputStream ois = new ObjectInputStream(new FileInputStream("person.txt"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
