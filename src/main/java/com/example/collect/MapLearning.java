package com.example.collect;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 吕茂陈
 */
@Slf4j
public class MapLearning {

    public static void main(String[] args) {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("java", 10);
        map.put("python", 9);
        map.put("c++", 8);
        map.put(null, null);

        // 放入重复的key时，新的value会覆盖原有的value，返回被覆盖的value
        Integer integer = map.put("c++", 11);
        log.info("{}", integer);

        // 判断是否包含指定的key
        log.info("{}", map.containsKey("ios"));
        // 判断是否包含指定的value
        log.info("{}", map.containsValue(10));

        // 获取所有key组成的集合
//        for (String key : map.keySet()) {
//            log.info("{}",map.get(key));
//        }
        map.forEach((s, integer1) -> log.info("{}", integer1));
    }


    @Test
    public void main() {
        Map<String, Integer> map = new HashMap<>();
        map.put("java", 109);
        map.put("ios", 10);
        map.put("ajax", 79);
        log.info("{}", map.put("java", 99));
        log.info("{}", map);
        log.info("{}", map.containsKey("ios"));
        log.info("{}", map.containsValue(79));
        log.info("{}", "--------------");
        for (String key :
            map.keySet()) {
            log.info("{}", key + "---" + map.get(key));
        }
    }


    @Test
    public void test01() {
        System.out.println(MapUtil.builder("id", "policyId").build());
        System.out.println(MapUtil.builder("id", "policyId").map());
    }


    @Test
    public void test02() {
        Map<String, Object> ht = new HashMap<>();
        ht.get(null);


        Map<Object, Object> of = Map.of();
        HashMap<Object, Object> map = new HashMap<>(of);
        map.get(null);

        // NullPointerException
        of.get(null);
    }


    @Test
    public void test03() {
        HashMap<String, Integer> prices = new HashMap<>();

        prices.put("Shoes", 180);
        prices.put("Bag", 300);

        prices.computeIfAbsent("Shoes", k -> Integer.valueOf(k+100));

        System.out.println(prices);


        Map<String, Set<String>> appOsMap = new HashMap<>();
        appOsMap.put("1", new HashSet<>(Set.of("windows", "ios")));
        appOsMap.put("2", new HashSet<>(Set.of("mac")));
        appOsMap.put("3", new HashSet<>(Set.of("mac")));

        appOsMap.computeIfPresent("1", (k,v) -> {
            HashSet<String> strings = new HashSet<>(v);
            strings.add("linux");
            return strings;
        });

        appOsMap.computeIfAbsent("3", k -> {
            HashSet<String> strings = new HashSet<>(appOsMap.get("2"));
            strings.add("linux");
            return strings;
        });
        System.out.println(appOsMap);

        // 如果
        appOsMap.computeIfAbsent("3", k -> new HashSet<>()).add("linux");
        System.out.println(appOsMap);
    }


    @Test
    public void test04() {
        Map<String, Integer> map = new HashMap<>();

        // 使用 computeIfAbsent 在键不存在时计算并插入值
        Integer value1 = map.computeIfAbsent("key1", k -> k.length());
        System.out.println("key1: " + value1); // 输出 key1: 4

        // 如果键已经存在，则返回现有的值
        Integer value2 = map.computeIfAbsent("key1", k -> k.length() * 2);
        System.out.println("key1: " + value2); // 输出 key1: 4

        System.out.println(map); // 输出 {key1=4}
    }


    @Test
    public void test05() {
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 4);

        // 使用 computeIfPresent 在键存在时计算并更新值
        Integer value1 = map.computeIfPresent("key1", (k, v) -> v * 2);
        System.out.println("key1: " + value1); // 输出 key1: 8

        // 如果键不存在，则不进行任何操作
        Integer value2 = map.computeIfPresent("key2", (k, v) -> v * 2);
        System.out.println("key2: " + value2); // 输出 key2: null

        System.out.println(map); // 输出 {key1=8}
    }

}
