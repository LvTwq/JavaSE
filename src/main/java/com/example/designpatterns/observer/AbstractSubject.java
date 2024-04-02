package com.example.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 * @description 抽象主题（抽象被观察者角色）
 * @date 2023/1/4 9:04
 */
public abstract class AbstractSubject {

    /**
     * 持有所有抽象观察者角色的集合引用
     */
    private final List<AbstractObserver> observers = new ArrayList<>();

    /**
     * 添加一个观察者
     *
     * @param observer 观察者
     */
    public void addObserver(AbstractObserver observer) {
        observers.add(observer);
    }

    /**
     * 移除一个观察者
     *
     * @param observer 观察者
     */
    public void removeObserver(AbstractObserver observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有的观察者，执行观察者更新方法
     *
     * @param context 通知内容
     */
    public void notifyObserver(String context) {
        observers.forEach(observer -> observer.receiveMsg(context));
    }
}
