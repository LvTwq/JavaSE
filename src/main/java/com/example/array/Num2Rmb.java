package com.example.array;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吕茂陈
 */
@Slf4j
public class Num2Rmb {

    public static void main(String[] args) {
        List<String> slList = Lists.newArrayList("9", "10", "11", "12", "13");
        List<String> arrFklx = new ArrayList<>();
        arrFklx.add("1");
        System.out.println(Collections.disjoint(arrFklx, slList));

        // 取交集
        boolean b = slList.retainAll(arrFklx);
        System.out.println(b);
        for (String s : slList) {
            System.out.println(s);
        }
    }



    /**
     * 将一个浮点数分解成整数部分和小数部分字符串
     *
     * @param num
     * @return 数组的第一个元素是小数部分，第二个元素是小数部分
     */
    private String[] divide(double num) {
        // 将一个浮点数强制转成long类型，得到它的整数部分
        long zheng = (long) num;
        // 小数部分乘以100后取整
        long xiao = Math.round((num - zheng) * 100);
        return new String[]{zheng + "", String.valueOf(xiao)};
    }




    /**
     * 把一个四位数字字符串转成汉字字符串
     *
     * @param numStr
     * @return
     */
    @Test
    public void toHanStr() {
        String[] hanArr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unitArr = {"十", "百", "千"};
        String numStr = "6109";
        String result = "";
        int numLen = numStr.length();
        for (int i = 0; i < numLen; i++) {
            // 把char型数字转成int，因为他们的ASCII码值相差48，例如‘6’转成6
            int num = numStr.charAt(i) - 48;
            // 如果不是最后一位数字，而且数字不是0，则添加单位（千，百，十）
            if (i != numLen - 1 && num != 0) {
                result += hanArr[num] + unitArr[numLen - 2 - i];
            } else {
                result += hanArr[num];
            }
        }
        log.info("{}", result);
    }


    @Test
    public void test01() {
        int[] arr = {1, 2, 3};
        // arr 的类型，是 int[]，asList方法把 int[]作为了泛型对象
        List<int[]> ints = Arrays.asList(arr);
        log.info("ints:{},size:{},class{}", ints, ints.size(), ints.get(0).getClass());

        log.info("==============================");
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        log.info("list:{},size:{},class{}", list, list.size(), list.get(0).getClass());
    }

    @Test
    public void test02() {
        String[] arr = {"1", "2", "3"};
        // 这个 ArrayList 是 Arrays 的内部类，继承自 AbstractList 类，并没有覆写父类的 add 方法，而父类中 add 方法的实现，就是抛出 UnsupportedOperationException
        List<String> list = Arrays.asList(arr);

        List<String> collect = Arrays.stream(arr).collect(Collectors.toList());
    }
}
