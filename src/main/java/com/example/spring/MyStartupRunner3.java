package com.example.spring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/23 15:08
 */
@Slf4j
@Component
public class MyStartupRunner3 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("33333333333");
    }
}
