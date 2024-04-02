package com.example.designpatterns.pipeline;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/11/23 11:14
 */
public class PipelineClient {

    public static void main(String[] args) {

        // 管道初始化
        Pipeline pipeline = new StandardPipeline();

        // value扩展
        PipelineValue pipelineValue = new GraySwitchValue();
        PipelineValue pipelineValue2 = new ForTestValue();

        // 上下文
        PipelineContext pipelineContext = new StandardPipelineContext();

        pipeline.addValue(pipelineValue);
        pipeline.addValue(pipelineValue2);

        // 调用管道
        pipeline.invoke(pipelineContext);

    }
}
