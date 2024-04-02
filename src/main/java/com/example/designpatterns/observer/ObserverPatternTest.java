package com.example.designpatterns.observer;

import org.junit.Test;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/1/4 9:05
 */
public class ObserverPatternTest {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver());
        subject.sendMsg("Hello World!");
    }


    @Test
    public void test01() {
        EventSource eventSource = new EventSource();
        eventSource.addListener(new CloseDoorListener());
        eventSource.addListener(new OpenDoorListener());

        eventSource.notifyListenerEvents(new DoorEvent("关门事件", -1));
        eventSource.notifyListenerEvents(new DoorEvent("开门事件", 1));
    }
}
