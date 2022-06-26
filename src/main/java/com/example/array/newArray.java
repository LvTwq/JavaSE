package com.example.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class newArray {
    public static void main(String[] args) {
        List<String> slList = Lists.newArrayList("9", "10", "11", "12", "13");
        List<String> arrFklx = new ArrayList<>();
        arrFklx.add("1");
        System.out.println(Collections.disjoint(arrFklx, slList));

        // 取交集
        boolean b = slList.retainAll(arrFklx);
        System.out.println(b);
        for (String s : slList) {
            System.out.println(s);
        }
    }


    @Test
    public void test01() {
        String a1 = "a1,a2,a3";
        String a2 = "a4";
        String s = "";
        s.split(",");
        List<String> list = Lists.newArrayList(a1, a2);
        System.out.println(list.size());
    }


    @Test
    public void test02(){
        String filename = "image/png";
        String[] split = filename.split("/");
        System.out.println(split[1]);
    }


}
