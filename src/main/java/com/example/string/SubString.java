package com.example.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吕茂陈
 * @date 2022/03/16 21:12
 */
@Slf4j
public class SubString {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param arrA int整型一维数组
     * @param K    int整型
     * @return int整型一维数组
     */
    public int[] tranArr(int[] arrA, int K) {
        // 先对原数组进行排序，计算得到最大差值
//        int max = Arrays.stream(arrA).max().getAsInt();
//        int min = Arrays.stream(arrA).min().getAsInt();
//        int cha = max - min;


        // T 的范围是 -K~K
        List<Integer> maxB = Arrays.stream(arrA).sorted().map(e -> e + K)
                .boxed().collect(Collectors.toList());
        List<Integer> minB = Arrays.stream(arrA).sorted().map(e -> e - K)
                .boxed().collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        for (Integer max : maxB) {
            minB.forEach(min -> result.add(max - min));
        }
//        result.
return null;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums   int整型一维数组
     * @param values int整型一维数组
     * @return int整型
     */
    public int getMaxValue(int[] nums, int[] values) {
        // 从 nums 中取值，第i次取的值x，values[i-1] * x

        // 从values中取值是按顺序从0开始取的
        // 所以values中的值要和nums当前
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int max = Arrays.stream(values).max().getAsInt();
        int result = 0;
        for (int value : values) {
            // 不是最大的，就取nums头尾中的较小值
            int i = 0;
            if (value < max) {
                i = value * getSmall(list);
            } else {
                i = value * getBig(list);
            }
            result = result + i;
        }
        return result;
    }

    /**
     * 获取数组头尾较小值的值，并把他删除
     *
     * @param list
     * @return
     */
    private int getSmall(List<Integer> list) {
        int first = list.get(0);
        int last = list.get(list.size() - 1);
        if (first < last) {
            list.remove(0);
            return first;
        }
        list.remove(list.size() - 1);
        return last;
    }

    private int getBig(List<Integer> list) {
        int first = list.get(0);
        int last = list.get(list.size() - 1);
        if (first < last) {
            list.remove(list.size() - 1);
            return last;
        }
        list.remove(0);
        return first;
    }

    public static void main(String[] args) {
        SubString test = new SubString();
        int[] nums = {1, 3, 5, 2, 4};
        int[] values = {1, 2, 3, 4, 5};
        System.out.println(test.getMaxValue(nums, values));
    }


    @Test
    public void removeDuplicates() {
        int[] nums = {1, 1, 2};
        List<Integer> list = Arrays.stream(nums).distinct().boxed().collect(Collectors.toList());
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        nums = result;
        log.info("{}", nums);
        log.info("{}", nums.length);
    }

}
