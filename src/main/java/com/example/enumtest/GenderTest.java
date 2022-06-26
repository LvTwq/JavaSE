package com.example.enumtest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class GenderTest {
    
    public static void main(String[] args) {
        // valueOf() 方法获取指定枚举类的枚举值
        Gender g = Gender.valueOf("FEMALE");
        g.setName("女");
        // 访问枚举值的 name 实例变量
        log.info(g + "代表" + g.getName());
        g.setName("男");
        log.info(g + "代表" + g.getName());
    }
}

@Slf4j
@Getter
enum Gender {
    // 枚举实例
    MALE, FEMALE;
    /**
     * 实例变量
     */
    private String name;

    void setName(String name) {
        switch (this) {
            case MALE:
                if ("男".equals(name)) {
                    this.name = name;
                } else {
                    log.info("参数错误");
                }
                break;
            case FEMALE:
                if ("女".equals(name)) {
                    this.name = name;
                } else {
                    log.info("参数错误");
                }
                break;
            default:
                break;
        }
    }
}
