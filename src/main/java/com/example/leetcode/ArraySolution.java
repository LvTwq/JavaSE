package com.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 1-100中的99个数（不重复）用a[99]存储，找出没存进去的那个数值
 *
 * @author 吕茂陈
 * @date 2022/05/28 11:22
 */
@Slf4j
public class ArraySolution {


    /**
     * 先判断1和100是否存在，不存在直接输出；
     * 不存在，再遍历数组，相邻元素相减，值为2，说明中间缺一个，且较小值+1
     * 时间复杂度为o(n^2)
     */
    @Test
    public void test01() {
        //定义一个1-100中缺少50的数组
        int[] array = new int[99];
        for (int i = 0; i < 49; i++) {
            array[i] = i + 1;
        }
        for (int i = 50; i < 100; i++) {
            array[i - 1] = i + 1;
        }
        Arrays.sort(array);
        // 如果第一个数是2，则说明1没存进去
        if (array[0] == 2) {
            log.info("{}", 1);
        }
        // 如果最后一个数是99，则说明100没存进去
        if (array[98] == 99) {
            log.info("{}", 100);
        }
        // 注意这里长度要-1，因为循环里面要用 i+1
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] - array[i] == 2) {
                log.info("{}", array[i] + 1);
            }
        }
    }


    /**
     * 时间复杂度为o(n)，空间复杂度为o(n)
     */
    @Test
    public void test02() {
        //定义一个1-100中缺少50的数组
        int[] array = new int[99];
        for (int i = 0; i < 49; i++) {
            array[i] = i + 1;
        }
        for (int i = 50; i < 100; i++) {
            array[i - 1] = i + 1;
        }
        Arrays.sort(array);
        // key:value = 值：坐标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], i);
        }
        for (int i = 1; i <= 100; i++) {
            if (null == map.get(i)) {
                log.info("{}", i);
            }
        }
    }


    /**
     * 异或
     * 时间复杂度为o(n)，空间复杂度为o(1)
     */
    @Test
    public void test03() {
        //定义一个1-100中缺少50的数组
        int[] array = new int[99];
        for (int i = 0; i < 49; i++) {
            array[i] = i + 1;
        }
        for (int i = 50; i < 100; i++) {
            array[i - 1] = i + 1;
        }
        Arrays.sort(array);
        // 转为 二进制，相同 异或 为0，不同 异或 为1；0和任何数 异或 为任何无数
        int temp = 0;
        for (int i = 1; i <= 99; i++) {
            // 这时 temp 为 i 坐标的值
            temp = temp ^ i;
            // i 和 i-1 异或
            temp = temp ^ array[i - 1];
        }
        temp = temp ^ 100;
        log.info("{}", temp);
    }

    @Test
    public void test04() {
        log.info("{}", 100 ^ 100);
        log.info("{}", 99 ^ 100);
    }

}
