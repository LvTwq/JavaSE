package com.example.designpatterns.pipeline;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:14
 */
public class ForTestValue extends AbstractPipelineValue {

    @Override
    public boolean doExec(PipelineContext pipelineContext) {

        return true;
    }
}
