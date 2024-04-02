package com.example.designpatterns.observer;

/**
 * @author 吕茂陈
 * @description 具体被观察者
 * @date 2023/1/4 9:04
 */
public class ConcreteSubject extends AbstractSubject {

    /**
     * 被观察者发送消息
     *
     * @param context 消息内容
     */
    public void sendMsg(String context) {
        System.out.println("具体被观察者角色发送消息: " + context);
        super.notifyObserver(context);
    }
}
