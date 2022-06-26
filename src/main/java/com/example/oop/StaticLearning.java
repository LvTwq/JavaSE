package com.example.oop;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕茂陈
 * @date 2021/11/12 08:18
 */
public class StaticLearning {

    private static final Map<String, String> map = new HashMap<>();

    /*
     * 静态初始化块，在类初始化阶段自动执行
     */
    static {
        map.put("1", "1");
        map.keySet().forEach(System.out::println);
    }

    {
        map.put("3", "3");
        map.keySet().forEach(System.out::println);
    }

    public static void main(String[] args) {
        map.put("2", "2");
        map.keySet().forEach(System.out::println);
    }
}
