package com.example.designpatterns;

/**
 * @author 吕茂陈
 */
public interface Command {

    /**
     * 封装“处理行为”
     * @param element
     */
    void process(int element);
}
