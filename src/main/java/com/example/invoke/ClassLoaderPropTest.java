package com.example.invoke;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author 吕茂陈
 */
public class ClassLoaderPropTest {

    public static void main(String[] args) throws IOException {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader);
        System.out.println("==============");
        /*
        获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定
        如果操作系统没有指定CLASSPATH环境变量，则默认以当前路径作为系统加载器的加载路径
         */
        Enumeration<URL> em1 = systemClassLoader.getResources("");
        while (em1.hasMoreElements()) {
            System.out.println(em1.nextElement());
        }
        System.out.println("==============");
        // 获取系统类加载器的父类加载器，得到扩展了加载器
        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parent：" + extensionLoader.getParent());
    }
}
