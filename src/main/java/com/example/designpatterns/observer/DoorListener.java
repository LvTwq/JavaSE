package com.example.designpatterns.observer;

import java.util.EventListener;

/**
 * @author 吕茂陈
 * @description 事件监听器接口
 * @date 2023/1/4 9:41
 */
public interface DoorListener extends EventListener {

    /**
     * 门处理事件
     *
     * @param doorEvent 事件
     */
    void doorEvent(DoorEvent doorEvent);
}
