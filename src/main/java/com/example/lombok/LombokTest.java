package com.example.lombok;

import org.junit.Test;

/**
 * @author 吕茂陈
 * @date 2021/09/26 17:36
 */
public class LombokTest {

    @Test
    public void test01() {
        Ajmc.AjmcBuilder builder = Ajmc.builder().id("1").name("123");
        System.out.println(builder);
        Ajmc ajmc = Ajmc.builder().id("1").name("123").build();
        System.out.println(ajmc);
    }
}
