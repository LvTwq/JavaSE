package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class LambdaAndInner {
    private int age = 12;
    private static String name = "吕茂陈";

    public void test() {
        String book = "Java";
        Displayable dis = () -> {
            System.out.println("book局部变量：" + book);
            System.out.println("外部类age实例变量：" + age);
            System.out.println("外部类name类变量：" + name);
        };
        dis.display();
        System.out.println(dis.add(3, 6));
    }

    public static void main(String[] args) {
        LambdaAndInner inner = new LambdaAndInner();
        inner.test();
    }
}
