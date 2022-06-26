package com.example.env;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author 吕茂陈
 */
public class SystemTest {
    public static void main(String[] args) throws IOException {
        // 获取系统所有的环境变量
        Map<String, String> env = System.getenv();
        for (String name :
                env.keySet()) {
            System.out.println(name + "--->" + env.get(name));
        }
        // 获取指定环境变量的值
        System.out.println(System.getenv("JAVA_HOME"));
        // 获取所有的系统属性
        Properties props = System.getProperties();
        props.store(new FileOutputStream("props.txt"), "System Properties");
        System.out.println(System.getProperty("os.name"));

    }
}
