package com.example.annotation;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * @author 吕茂陈
 */
public class ArrayTest {

    @Test
    public void test02() {
        Object arr = Array.newInstance(String.class, 3, 4, 10);
        Object arrObj = Array.get(arr, 2);
        Array.set(arrObj, 2, new String[]{
                "java", "c++"
        });
        Object anArr = Array.get(arrObj, 3);
        Array.set(anArr, 8, "php");
        String[][][] cast = (String[][][]) arr;
        System.out.println(cast[2][3][8]);
        System.out.println(cast[2][2][0]);
    }

    @Test
    public void test01() {
        // 创建一个元素类型为String，长度为10的数组
        Object arr = Array.newInstance(String.class, 10);
        // 为数组中index为5、6的元素赋值
        Array.set(arr, 5, "java");
        Array.set(arr, 6, "c++");
        // 取出数组中index为5、6的元素
        Object book1 = Array.get(arr, 5);
        Object book2 = Array.get(arr, 6);
        System.out.println(book1);
        System.out.println(book2);
    }
}
