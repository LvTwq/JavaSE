package com.example.env;

import org.springframework.core.env.Environment;

import cn.hutool.extra.spring.SpringUtil;

/**
 * @author 吕茂陈
 * @date 2022/01/11 10:45
 */
public class Main {

    public static void main(String[] args) {
        Environment env = SpringUtil.getBean(Environment.class);
        env.getProperty("ip");
    }
}
