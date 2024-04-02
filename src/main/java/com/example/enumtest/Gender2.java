package com.example.enumtest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吕茂陈
 */
//@AllArgsConstructor
@Getter
public enum Gender2 {
    // 此处的枚举值必须调用对应的构造器来创建
    MALE("男"), FEMALE("女");
    private final String name;

    /**
     * 枚举类的构造器只能用private修饰
     *
     * @param name
     */
    Gender2(String name) {
        this.name = name;
    }


}
