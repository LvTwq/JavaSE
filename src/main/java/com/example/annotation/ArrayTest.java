package com.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
@Slf4j
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
        log.info("{}", cast[2][3][8]);
        log.info("{}", cast[2][2][0]);
    }

    @Test
    public void test01() {
        // 创建一个元素类型为String，长度为10的数组
        String[] arr = (String[]) Array.newInstance(String.class, 10);
        // 为数组中index为5、6的元素赋值
        Array.set(arr, 5, "java");
        Array.set(arr, 6, "c++");
        // 取出数组中index为5、6的元素
        String book1 = (String) Array.get(arr, 5);
        String book2 = (String) Array.get(arr, 6);
        log.info("book1:{}", book1);
        log.info("book2:{}", book2);
        log.info("arr:{}", (Object) arr);
    }


    public <T> T[] newInstance(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length);
    }

    @Test
    public void test03() {
        // 创建一维数组
        String[] arr = newInstance(String.class, 10);
        // 创建二维数组
        int[][] intArr = newInstance(int[].class, 5);
        arr[5] = "疯狂Java讲义";
        intArr[1] = new int[]{23, 12};
        log.info("{}", arr[5]);
        log.info("{}", intArr[1][1]);
    }

    /**
     * 初始化一个具有1个元素的数组，然后通过获取这个数组的值来获取基本类型默认值
     */
    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream.of(
            boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class
    ).collect(Collectors.toMap(Function.identity(), clazz -> Array.get(Array.newInstance(clazz, 1), 0)));


    @Test
    public void test04() {
        log.info("{}", DEFAULT_VALUES.get(short.class));
        Object o = Array.newInstance(int.class, 1);
        Object i = Array.get(o, 0);
        log.info("{}", i);
    }
}
