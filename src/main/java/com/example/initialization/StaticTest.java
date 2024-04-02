package com.example.initialization;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/8 10:42
 */
@Slf4j
public class StaticTest {

    public static String WAF_HTTP_POLICY = "1";

    public static void main(String[] args) {
        log.info("{}", WAF_HTTP_POLICY);
        WAF_HTTP_POLICY = "2";
        log.info("{}", WAF_HTTP_POLICY);
    }
}
