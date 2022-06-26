package com.example.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吕茂陈
 */
public class DiamondTest {
    public static void main(String[] args) {
        List<String> books = new ArrayList<>();
        books.add("java");
        books.add("go");
        books.forEach(ele -> System.out.println(ele.length()));

        Map<String, List<String>> schoolsInfo = new HashMap<>();
        List<String> schools = new ArrayList<>();
        schools.add("南大");
        schools.add("南工大");
        schoolsInfo.put("Tom", schools);
        schoolsInfo.forEach((key, value) -> System.out.println(key + "->" + value));
    }
}
