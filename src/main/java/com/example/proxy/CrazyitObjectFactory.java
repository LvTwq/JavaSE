package com.example.proxy;

import org.junit.Test;

import javax.swing.*;
import java.util.Date;

/**
 * @author 吕茂陈
 */
public class CrazyitObjectFactory {

    @Test
    public void test() {
        // 需要强转
        Date d = (Date) CrazyitObjectFactory.getInstance("java.util.Date");
        // 运行时会报错：ClassCastException
        JFrame frame = (JFrame) CrazyitObjectFactory.getInstance("java.util.Date");
        Date d2 = CrazyitObjectFactory.getInstance2(Date.class);
        System.out.println(d);
        System.out.println(d2);
    }

    public static <T> T getInstance2(Class<T> cls) {
        try {
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getInstance(String clsName) {
        try {
            Class<?> cls = Class.forName(clsName);
            return cls.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
