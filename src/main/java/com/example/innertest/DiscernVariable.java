package com.example.innertest;

/**
 * @author 吕茂陈
 */
public class DiscernVariable {

    private String prop = "外部类的实例变量";

    private class InClass {
        private String porp = "内部类的实例变量";

        public void info() {
            String porp = "局部变量";

            System.out.println("外部类的实例变量值：" + DiscernVariable.this.prop);
            System.out.println("内部类的实例变量值：" + this.porp);
            System.out.println("局部变量值：" + porp);
        }
    }

    public void test() {
        InClass inClass = new InClass();
        inClass.info();
    }

    public static void main(String[] args) {
        new DiscernVariable().test();
    }
}
