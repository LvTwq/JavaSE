package com.example.clazz;

import java.lang.reflect.Field;

/**
 * @author Administrator
 */
public class FieldTest {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Class<Person> personClass = Person.class;
        Field nameField = personClass.getDeclaredField("name");
        // 设置通过反射访问该成员变量时取消权限检查
        nameField.setAccessible(true);
        nameField.set(p, "吕茂陈");
        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(p, 23);
        System.out.println(p);
    }
}
