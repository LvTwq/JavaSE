package com.example.annotation;

/**
 * 重复注解只是一种简化写法，多个重复注解会被作为“容器”注解的value成员变量的数组元素
 *
 * @author 吕茂陈
 */
//@FkTags(Person.class)
@FkTag(name = "小红", value = 9)
public class FkTagTest {
    public static void main(String[] args) {
        Class<FkTagTest> clazz = FkTagTest.class;
        // 使用Java8的getDeclaredAnnotationsByType()，获取FkTagTest类的多个@FkTag注解
        FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
//        for (FkTag tag : tags) {
//            System.out.println(tag.name() + "-->" + tag.age());
//        }
        // 使用传统的getDeclaredAnnotation()方法获取修饰FkTagTest类的@FkTags注解，虽然上面源码没有显示使用@FkTags
        // 但程序使用了两个@FkTag注解修饰该类，系统会自动将两个@FkTag注解作为@FkTags的value成员变量的数组元素处理
        FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
        System.out.println(container);

        int i = 2147483647;

    }
}
