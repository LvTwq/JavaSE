package com.example.annotation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;


/**
 * @author 吕茂陈
 */
public class User {

    /**
     * 不希望生成它的get/set方法
     */
    @Getter(AccessLevel.NONE)
    private String phone;

    /**
     * 生成getter方法时，在getter方法上添加annotation
     */
    @Getter(onMethod_ = {@JsonProperty("email")})
    private String email;

    /**
     * 标注字段为懒加载字段，懒加载字段在创建对象时不会进行初始化，而是在第一次访问的时候才会初始化，后面再次访问也不会重复初始化
     */
    @Getter(lazy = true)
    private final List<String> cityList = getCityFromCache();

    private List<String> getCityFromCache() {
        System.out.println("get city from cache ...");
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // 不会执行getCityFromCache()方法
        User user = new User();
    }

}
