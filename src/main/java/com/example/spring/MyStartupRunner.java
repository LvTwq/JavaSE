package com.example.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/23 15:01
 */
@Order(1)
@Slf4j
@Component
public class MyStartupRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.info("1111111111" );
    }
}
