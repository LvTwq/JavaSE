package com.example.designpatterns.observer;

/**
 * @author 吕茂陈
 * @description 抽象观察者角色
 * @date 2023/1/4 9:03
 */
public abstract class AbstractObserver {

    /**
     * 接收消息
     *
     * @param context 消息内容
     */
    public abstract void receiveMsg(String context);

}
