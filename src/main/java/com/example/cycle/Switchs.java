package com.example.cycle;

import org.junit.Test;

public class Switchs {
    public static void main(String[] args) {
        method("sth");
        // NullPointerException
//        method(null);
    }

    /**
     * 当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null 判断。
     *
     * @param param
     */
    public static void method(String param) {
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
//                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }


    @Test
    public void test01() {
        int i=0;
        if (i ==0) {
            System.out.println("1111");
            i = 1;
        } else if (i != 0) {
            System.out.printf("2222");
        }
        System.out.println(String.valueOf(i));
    }
}
