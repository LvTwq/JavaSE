package com.example.invoke;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author 吕茂陈
 */
@Slf4j
public class FieldTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Class<Person> personClass = Person.class;
        // 获取Person名为name的成员变量
        // 使用 getDeclaredField() 可以获取各种访问控制符的成员变量，getField() 只能获取public的
        Field nameField = personClass.getDeclaredField("name");
        // 设置通过反射访问该成员变量时取消访问权限检查
        nameField.setAccessible(true);
        // 为person对象的name成员变量赋值
        nameField.set(person, "吕茂陈");
        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(person, 25);
        log.info("{}", person);
    }

    static class Person {
        private String name;
        private int age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}


