package com.example.training;

public class test1 {

    public static void main(String[] args) {
        // a 是一个引用
        String a = new String("ab");
        // b 是另一个引用，对象内容一样
        String b = new String("ab");
        // 放在常量池中
        String aa = "ab";
        // 从常量池中查找
        String bb = "ab";
        if (aa == bb) {
            System.out.println("aa==bb");
        }
        if (a == b) {
            System.out.println("a==b");
        }
        if (a.equals(b)) {
            System.out.println("a EQ b");
        }

        System.out.println(42 == 42.0);

        // 使用常量池中的对象
        Integer i1=40;
        // 创建新对象
        Integer i2 = new Integer(40);

    }
}
