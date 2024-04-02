package com.example.invoke;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/6 10:35
 */
@Slf4j
public class MyClassLoader extends ClassLoader {
    /*
     通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。
     比如应用是通过网络来传输 Java 类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。
     自定义类加载器一般都是继承自 ClassLoader 类，从上面对 loadClass 方法来分析来看，我们只需要重写 findClass 方法即可。
     */

    @Getter
    @Setter
    private String root;

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }


    private byte[] loadClassData(String className) throws FileNotFoundException {
        String fileName = root + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";

        try (FileInputStream ins = new FileInputStream(fileName);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setRoot("D:\\temp");
        try {
            Class<?> testClass = myClassLoader.loadClass("com.example.invoke.Test2");
            Object object = testClass.getDeclaredConstructor().newInstance();
            log.info("{}", object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
