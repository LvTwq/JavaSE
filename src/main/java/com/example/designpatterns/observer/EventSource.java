package com.example.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/1/4 9:42
 */
public class EventSource {

    /**
     * 监听器列表，监听器的注册则加入此列表
     */
    private final List<DoorListener> listenerList = new ArrayList<>();

    /**
     * 注册监听器
     */
    public void addListener(DoorListener eventListener) {
        listenerList.add(eventListener);
    }

    /**
     * 撤销注册
     */
    public void removeListener(DoorListener eventListener) {
        listenerList.remove(eventListener);
    }

    /**
     * 接受外部事件
     */
    public void notifyListenerEvents(DoorEvent event) {
        for (DoorListener eventListener : listenerList) {
            eventListener.doorEvent(event);
        }
    }
}
