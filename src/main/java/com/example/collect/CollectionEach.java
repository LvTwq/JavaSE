package com.example.collect;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吕茂陈
 */
@Slf4j
public class CollectionEach {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 5, 9, 5, 5, 4, 8, 5, 5);

        HashMap<Integer, Integer> map = new HashMap<>();
        list.forEach(e -> {
            int integer = map.getOrDefault(e, 0);
            integer++;
            map.put(e, integer);
        });
        Integer key = map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        log.info("{}", key);

    }
}
