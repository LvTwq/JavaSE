package com.example.generic;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/05/28 14:08
 */
@Slf4j
public class PlateTest {

    @Test
    public void test01() {
        Plate<? extends Number> p = new Plate<>(new Integer(1));

        // 不能存入，编译报错
//        p.setItem(new Integer(2));
//        p.setItem(new Double(2));

        // 只能取
        Number item = p.getItem();
        // 报错
//        Integer item2 = p.getItem();
        Object item3 = p.getItem();
        log.info("{},{}", item, item3);
    }


    @Test
    public void test02() {
        Plate<? super Integer> p = new Plate<>(new Integer(1));

        // 可以存
        p.setItem(new Integer(2));

        // 不能取
//        Integer i = p.getItem();
//        Number i2 = p.getItem();
        // 只能取出来放到Object
        Object i3 = p.getItem();
        log.info("{}", i3);
    }
}
