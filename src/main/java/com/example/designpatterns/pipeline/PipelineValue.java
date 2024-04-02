package com.example.designpatterns.pipeline;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:13
 */
public interface PipelineValue {

    /**
     * 节点执行
     *
     * @param pipelineContext
     * @return
     */
    boolean execute(PipelineContext pipelineContext);

}
