package com.example.designpatterns.decorator;

/**
 * @author 吕茂陈
 */
public abstract class Beverage {
    String description = "星巴克";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
