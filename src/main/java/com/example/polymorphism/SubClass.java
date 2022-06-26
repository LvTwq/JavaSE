package com.example.polymorphism;

/**
 * @author 吕茂陈
 */
public class SubClass extends BaseClass {

    public String book = "Java";

    @Override
    public void test() {
        System.out.println("子类覆盖父类的方法");
    }

    public void sub() {
        System.out.println("子类的普通方法");
    }

//    public static void testStatic(){
//        System.out.println("子类的静态方法");
//    }


    @Override
    public void test01() {
        super.test01();
    }

    public static void main(String[] args) {
        // 子类是一种特殊的父类，因此Java允许把一个子类对象直接赋值给一个父类引用变量（向上转型）

        BaseClass ploy = new SubClass();

        // 引用变量在编译阶段只能调用其编译时类型所具有的方法，在运行时则执行它运行时类型所具有的方法
        // 所以，与方法不同，对象的实例变量不具备多态性，系统只会访问它编译时所定义的成员变量
        System.out.println(ploy.book);

        ploy.base();

        // 当运行时调用该引用变量的方法时，其方法行为总是呈现出子类方法的行为特征
        // 所以会调用子类重写过的方法
        ploy.test();

        // 编译时类型为BaseClass，无sub()方法，所以编译报错
//        ploy.sub();

        BaseClass.testStatic();
        SubClass.testStatic();

    }
}
