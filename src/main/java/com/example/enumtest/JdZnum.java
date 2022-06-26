package com.example.enumtest;

import lombok.AllArgsConstructor;

/**
 * @author 吕茂陈
 * @date 2022/04/05 17:35
 */
@AllArgsConstructor
public enum JdZnum {

    /**
     *
     */
    START("1", "开始"),
    ING("2", "开始"),
    END("3", "开始");

    void setCode(String code) {
        this.code = code;
    }

    void setDesc(String desc) {
        this.desc = desc;
    }

    String code;
    String desc;
}
