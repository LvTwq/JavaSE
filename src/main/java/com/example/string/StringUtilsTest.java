package com.example.string;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class StringUtilsTest {

    public static void main(String[] args) {
        log.info("{}",StringUtils.isEmpty(null));
        log.info("{}",StringUtils.isBlank(null));
        log.info("{}",StringUtils.isBlank("     "));
        log.info("{}",StringUtils.isBlank("\t \n \f \r"));
        log.info("{}",StringUtils.trim("    erjigho    "));
        log.info("{}",StringUtils.strip("    erjigho    "));

    }


    @Test
    public void test01() {
        // 全部不为空：true；有一个为空：false
        log.info("{}", StringUtils.isNoneEmpty("", "222", "1"));
        // 有一个不为空：true
        log.info("{}", !StringUtils.isAllEmpty("", "222222"));
    }
}
