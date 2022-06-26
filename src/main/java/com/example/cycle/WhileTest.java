package com.example.cycle;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2021/12/29 08:45
 */
@Slf4j
public class WhileTest {

    @Test
    public void test01() {
        List<String> list = Lists.newArrayList("1", "2", "4", "4");
        while (list.contains("4")) {
            System.out.println("!!!");
        }
    }

    @Test
    public void test02() {
        List<String> list = Lists.newArrayList("1", "2", "4", "4");
        if (list.contains("4")) {
            System.out.println("!!!");
        }
    }


    @Test
    public void test03(){
        while (true) {
            log.info("!!!!!!!!!!");
            throw new RuntimeException("异常！");
        }
    }
}
