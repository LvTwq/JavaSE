package com.example.designpatterns.observer;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/1/4 9:42
 */
public class OpenDoorListener implements DoorListener {

    @Override
    public void doorEvent(DoorEvent doorEvent) {
        if (doorEvent.getState() == 1) {
            System.out.println("门打开了");
        }
    }
}
