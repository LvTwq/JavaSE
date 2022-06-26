package com.example.collect;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author 吕茂陈
 */
public class MapLearning {
    public static void main(String[] args) {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("java", 10);
        map.put("python", 9);
        map.put("c++", 8);
        map.put(null,null);

        // 放入重复的key时，新的value会覆盖原有的value，返回被覆盖的value
        Integer integer = map.put("c++", 11);
        System.out.println(integer);

        // 判断是否包含指定的key
        System.out.println(map.containsKey("ios"));
        // 判断是否包含指定的value
        System.out.println(map.containsValue(10));

        // 获取所有key组成的集合
//        for (String key : map.keySet()) {
//            System.out.println(map.get(key));
//        }
        map.forEach((s, integer1) -> System.out.println(integer1));
    }
}
