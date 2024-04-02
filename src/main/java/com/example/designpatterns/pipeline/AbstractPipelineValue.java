package com.example.designpatterns.pipeline;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:13
 */
@Slf4j
public abstract class AbstractPipelineValue implements PipelineValue {

    @Override
    public boolean execute(PipelineContext pipelineContext) {

        log.info(this.getClass().getSimpleName() + " start ");

        boolean result = doExec(pipelineContext);

        log.info(this.getClass().getSimpleName() + " end ");

        return result;
    }

    protected abstract boolean doExec(PipelineContext pipelineContext);
}
