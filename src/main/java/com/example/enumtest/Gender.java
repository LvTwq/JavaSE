package com.example.enumtest;

import lombok.Getter;

/**
 * @author 吕茂陈
 */
@Getter
public enum Gender {
    // 枚举实例
    MALE, FEMALE;
    /**
     * 实例变量
     */
    private String name;

    public void setName(String name) {
        switch (this) {
            case MALE:
                if ("男".equals(name)) {
                    this.name = name;
                } else {
                    System.out.println("参数错误");
                    return;
                }
                break;
            case FEMALE:
                if ("女".equals(name)) {
                    this.name = name;
                } else {
                    System.out.println("参数错误");
                    return;
                }
                break;
        }
    }
}
