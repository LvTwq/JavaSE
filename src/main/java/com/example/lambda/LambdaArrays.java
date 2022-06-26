package com.example.lambda;

import java.util.Arrays;

/**
 * @author 吕茂陈
 */
public class LambdaArrays {
    public static void main(String[] args) {
        String[] arr1 = {"java", "python", "php", "ios"};
        // 按字符串长度排序
        Arrays.parallelSort(arr1, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {3, -1, 25, 16, 30, 18};
        // 根据前两个元素计算当前元素值
        Arrays.parallelPrefix(arr2, ((left, right) -> left * right));
        System.out.println(Arrays.toString(arr2));

        long[] arr3 = new long[5];
        // 根据元素的索引计算当前元素的值
        Arrays.parallelSetAll(arr3, operand -> operand * 5);
        System.out.println(Arrays.toString(arr3));
    }
}
