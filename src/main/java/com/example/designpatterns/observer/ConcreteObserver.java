package com.example.designpatterns.observer;

/**
 * @author 吕茂陈
 * @description 具体观察者角色实现类
 * @date 2023/1/4 9:04
 */
public class ConcreteObserver extends AbstractObserver {

    @Override
    public void receiveMsg(String context) {
        System.out.println("具体观察者角色接收消息: " + context);
    }
}
