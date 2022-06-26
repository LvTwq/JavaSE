package com.example.collect;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吕茂陈
 */
public class FixedSizeList {
    public static void main(String[] args) {
        List<String> fixedList = Arrays.asList("java", "python");
        System.out.println(fixedList.getClass());
        fixedList.forEach(System.out::println);

        // 试图增加，删除元素都会引发异常
        fixedList.add("javascript");
        fixedList.remove("java");
    }
}
