package com.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/28 17:31
 */
@Slf4j
public class UtilTest {

    @Test
    public void test01() {
        final PathMatcher pathMatcher = new AntPathMatcher();

        String str = "100400:0";

        System.out.println(pathMatcher.match("100400", str));
        System.out.println(pathMatcher.match("100400:0", str));
        System.out.println(pathMatcher.match("100400:1", str));
    }
}
