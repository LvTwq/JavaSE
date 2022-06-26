package com.example.collect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕茂陈
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("java", 109);
        map.put("ios", 10);
        map.put("ajax", 79);
        System.out.println(map.put("java", 99));
        System.out.println(map);
        System.out.println(map.containsKey("ios"));
        System.out.println(map.containsValue(79));
        System.out.println("--------------");
        for (String key :
                map.keySet()) {
            System.out.println(key + "---" + map.get(key));
        }
    }
}
