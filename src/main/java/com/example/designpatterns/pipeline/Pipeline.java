package com.example.designpatterns.pipeline;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:11
 */
public interface Pipeline {

    /**
     * 执行
     *
     * @return
     */
    boolean invoke(PipelineContext pipelineContext);

    /**
     * 添加值
     *
     * @param pipelineValue
     * @return
     */
    boolean addValue(PipelineValue pipelineValue);

    /**
     * 移除值
     *
     * @param pipelineValue
     * @return
     */
    boolean removeValue(PipelineValue pipelineValue);
}