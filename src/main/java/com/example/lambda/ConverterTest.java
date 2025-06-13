package com.example.lambda;


import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ConverterTest {
    public static void main(String[] args) {
        // 用 lambda 来创建对象
        // Integer.valueOf(from) 实现了 convert() 方法
        Converter converter1 = from -> Integer.valueOf(from);

        // 使用方法引用创建对象：引用类方法
        // 类方法引用，也就是调用Integer类的valueOf()类方法来实现Converter函数式接口中唯一的抽象方法
        // 当调用Converter接口中唯一的抽象方法时，调用参数会传给Integer类的valueOf()类方法
        Converter converter2 = Integer::valueOf;
        Integer val1 = converter1.convert("99");
        Integer val2 = converter2.convert("88");
        log.info("{}",val1);
        log.info("{}",val1.getClass());
        log.info("{}",val2);


        // 引用特定对象的实例方法
        Converter converter3 = from -> "fkit.org".indexOf(from);
        Converter converter4 = "fkit.org"::indexOf;

        log.info("{}",converter3.convert("it"));
        log.info("{}",converter4.convert("it"));

        // 引用某类对象的实例方法
        MyTest mt = (a, b, c) -> a.substring(b, c);
        String str = mt.test("java i love you", 2, 9);
        log.info("{}",str);
        // 函数式接口中被实现方法的第一个参数作为调用者，后面的参数全部传给该方法作为参数
        MyTest mt1 = String::substring;
        String str1 = mt1.test("java i love you", 2, 9);
        log.info("{}",str1);

        // 引用构造器
        YourTest yt = a -> new JFrame(a);
        JFrame jf = yt.win("我的窗口");
        log.info("{}",jf);
        YourTest yt1 = JFrame::new;
        JFrame jf1 = yt1.win("我的窗口");
        log.info("{}",jf1);

    }

    @FunctionalInterface
    interface MyTest {
        String test(String a, int b, int c);
    }

    @FunctionalInterface
    interface YourTest {
        JFrame win(String title);
    }

    @FunctionalInterface
    interface Displayable {
        void display();

        default int add(int a, int b) {
            return a + b;
        }
    }

}
