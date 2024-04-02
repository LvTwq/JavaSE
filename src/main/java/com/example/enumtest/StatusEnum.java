package com.example.enumtest;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/6/12 9:38
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 状态
     */
    INIT("I", "初始化"),
    PROCESSING("P", "处理中"),
    SUCCESS("S", "成功"),
    FAIL("F", "失败");

    private String code;
    private String desc;


    static {
        // 通过名称构建缓存,通过EnumCache.findByName(StatusEnum.class,"SUCCESS",null);调用能获取枚举
        EnumCache.registerByName(StatusEnum.class, StatusEnum.values());
        // 通过code构建缓存,通过EnumCache.findByValue(StatusEnum.class,"S",null);调用能获取枚举
        EnumCache.registerByValue(StatusEnum.class, StatusEnum.values(), StatusEnum::getCode);
    }
}
