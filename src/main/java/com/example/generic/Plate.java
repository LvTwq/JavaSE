package com.example.generic;

/**
 * @author 吕茂陈
 * @date 2022/05/28 14:07
 */
public class Plate<T> {

    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
