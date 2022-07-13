package com.example.net;

import cn.hutool.core.lang.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author 吕茂陈
 * @date 2022-07-11 10:46
 */
@Slf4j
public class UrlTest {


    @Test
    public void test01() {
        boolean url = Validator.isUrl("https://blog.csdn.net/dabing69221/article/details/16996877");
        log.info("{} ", url);
    }
}
