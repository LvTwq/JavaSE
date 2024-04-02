package com.example.designpatterns.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.EventObject;

/**
 * @author 吕茂陈
 * @description 事件对象
 * @date 2023/1/4 9:40
 */
public class DoorEvent extends EventObject {

    @Getter
    @Setter
    private int state;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source) {
        super(source);
    }

    public DoorEvent(Object source, int state) {
        super(source);
        this.state = state;
    }

}
