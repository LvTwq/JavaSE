package com.example.guava;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/24 10:27
 */
@Slf4j
public class RangeMapTest {

    @Test
    public void test01() {
        String s = new BigDecimal("0.66").setScale(0, RoundingMode.HALF_UP).toPlainString();
        System.out.println(s);
        RangeMap<Comparable, Integer> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(0, 25), 200);
        rangeMap.put(Range.closed(26, 60), 120);
        System.out.println(rangeMap.get(-1));
        System.out.println(rangeMap.get(0));
        System.out.println(rangeMap.get(25));
        System.out.println(rangeMap.get(26));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(1.5));
    }
}
