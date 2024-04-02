package com.example.designpatterns.pipeline;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:13
 */
public class StandardPipelineContext implements PipelineContext {

    private Map<String, Object> contentMap = Maps.newConcurrentMap();

    @Override
    public void set(String contextKey, Object contextValue) {
        contentMap.put(contextKey, contextValue);
    }

    @Override
    public Object get(String contextKey) {
        return contentMap.get(contextKey);
    }
}
