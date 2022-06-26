package com.example.collect;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 吕茂陈
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.setProperty("username", "吕茂陈");
        props.setProperty("password", "root");

        props.store(new FileOutputStream("a.ini"), "comment line");
        Properties props2 = new Properties();
        props2.setProperty("gender", "male");
        props2.load(new FileInputStream("a.ini"));
        System.out.println(props2);
    }
}
