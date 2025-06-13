package com.example.designpatterns.factory;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/8/28 14:12
 */
public interface SceneHandleBase<A, T, G> {

    /**
     * 执行方法
     */
    G doCallback(T params);

}