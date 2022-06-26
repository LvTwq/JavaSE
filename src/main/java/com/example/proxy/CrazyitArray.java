package com.example.proxy;

import java.lang.reflect.Array;

/**
 * @author 吕茂陈
 */
public class CrazyitArray {

    public static <T> T[] newInstance(Class<T> componentType, int length) {
        return (T[]) Array.newInstance(componentType, length);
    }

    public static void main(String[] args) {
        // 创建一维数组
        String[] arr = CrazyitArray.newInstance(String.class, 10);
        // 创建二维数组
        int[][] intArr = CrazyitArray.newInstance(int[].class, 5);
        arr[5] = "疯狂Java讲义";
        intArr[1] = new int[]{23, 12};
        System.out.println(arr[5]);
        System.out.println(intArr[1][1]);
    }
}
