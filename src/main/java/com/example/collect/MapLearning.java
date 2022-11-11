package com.example.collect;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
}
