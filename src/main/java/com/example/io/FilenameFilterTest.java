package com.example.io;

import java.io.File;

/**
 * @author 吕茂陈
 */
public class FilenameFilterTest {
    public static void main(String[] args) {
        // 以当前路径创建一个File对象
        File file = new File(".");
        // 如果文件名以 .java 结尾，或者文件对应一个路径，则返回true
        String[] nameList = file.list(((dir, name) -> name.endsWith(".java")
                || new File(name).isDirectory()));
        for (String name :
                nameList) {
            System.out.println(name);
        }
    }
}
