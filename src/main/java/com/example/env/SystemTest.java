package com.example.env;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author 吕茂陈
 */
@Slf4j
public class SystemTest {
    public static void main(String[] args) throws IOException {
        // 获取系统所有的环境变量
        Map<String, String> env = System.getenv();
        for (String name :
                env.keySet()) {
            log.info(name + "--->" + env.get(name));
        }
        // 获取指定环境变量的值
        log.info(System.getenv("JAVA_HOME"));
        // 获取所有的系统属性
        Properties props = System.getProperties();
        props.store(new FileOutputStream("props.txt"), "System Properties");
        log.info(System.getProperty("os.name"));


        Environment environment = SpringUtil.getBean(Environment.class);
        log.info(environment.getProperty("ip"));
    }



    



}