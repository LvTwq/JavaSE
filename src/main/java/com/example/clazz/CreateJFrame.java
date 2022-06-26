package com.example.clazz;

import java.lang.reflect.Constructor;

public class CreateJFrame {
    public static void main(String[] args) throws Exception {
        // 获取JFrame对应的CLass对象
        Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        // 获取JFrame中带一个字符串参数的构造器
        Constructor<?> ctor = jframeClazz.getConstructor(String.class);
        // 调用newInstance方法创建对象
        Object obj = ctor.newInstance("测试窗口");
        System.out.println("obj = " + obj);
    }
}
