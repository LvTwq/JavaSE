package com.example.lombok;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author 吕茂陈
 * @date 2021/09/26 17:36
 */
@Slf4j
public class LombokTest {

    @Test
    public void test01() {
        Ajmc.AjmcBuilder builder = Ajmc.builder().id("1").name("123");
        log.info("{}", builder);
        Ajmc ajmc = Ajmc.builder().id("1").name("123").build();
        log.info("{}", ajmc);
    }


    @Test
    @SneakyThrows
    public void test02() {
        int i = 1 / 0;
    }
}
