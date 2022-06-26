package com.example.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 */
public class ErasureTest {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        System.out.println(li.get(0));
        // 丢失泛型信息
        List list = li;
        List<String> ls = list;
        System.out.println(ls.get(0));
    }
}
