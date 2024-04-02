package com.example.designpatterns.pipeline;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:12
 */
public interface PipelineContext {

    String FOR_TEST = "forTest";

    /**
     * 设置
     *
     * @param contextKey
     * @param contextValue
     */
    void set(String contextKey, Object contextValue);

    /**
     * 获取值
     *
     * @param contextKey
     * @return
     */
    Object get(String contextKey);
}
