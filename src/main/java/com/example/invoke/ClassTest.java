package com.example.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unchecked")
@Anno
public class ClassTest {

    private ClassTest() {
    }

    public ClassTest(String name) {
        System.out.println("有參數的构造器");
    }

    public void replace(String str, List<String> list) {

    }

    public void info() {
        System.out.println("执行无参数的info方法");
    }

    public void info(String str) {
        System.out.println("执行有参数的info方法，参数为：" + str);
    }

    class Inner {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<ClassTest> clazz = ClassTest.class;
        Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        System.out.println("=========ClassTest的全部构造器如下：==========");
        Arrays.stream(ctors).forEach(System.out::println);
        System.out.println("==========================");
        // 使用 class.forName() 方法加载ClassTest的Inner内部类
        Class<?> inClazz = Class.forName("com.example.invoke.ClassTest$Inner");
        System.out.println("inClazz对应类的外部类为：" + inClazz.getDeclaringClass());

        test01();
    }

    public static void test01() throws NoSuchMethodException {
        Class<ClassTest> clazz = ClassTest.class;
        // 获取replace()方法
        Method replace = clazz.getMethod("replace", String.class, List.class);
        System.out.println("replace()方法参数个数：" + replace.getParameterCount());
        Parameter[] parameters = replace.getParameters();
        AtomicInteger index = new AtomicInteger(1);
        Arrays.stream(parameters).forEach(e ->
        {
            System.out.println("====第" + index.getAndIncrement() + "个参数信息====");
            System.out.println("参数名：" + e.getName());
            System.out.println("形参类型：" + e.getType());
            System.out.println("泛型类型：" + e.getParameterizedType());
        });
    }
}
