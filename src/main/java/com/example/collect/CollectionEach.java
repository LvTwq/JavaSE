package com.example.collect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        // 初始化以后，又进行了一次赋值操作，进行了二次修改，所以编译器认为这不是一个final变量故而报错
        String id = "";
        if (StringUtils.isNotBlank("5")) {
            id = "6";
        } else {
            id = "7";
        }
        list.forEach(e -> {
//            String s = e + id;
        });
    }
}
