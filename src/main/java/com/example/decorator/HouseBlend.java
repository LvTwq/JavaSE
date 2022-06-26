package com.example.decorator;

/**
 * @author 吕茂陈
 */
public class HouseBlend extends Beverage {
    @Override
    public double cost() {
        return 0.89;
    }

    public HouseBlend() {
        description = "首选咖啡";
    }
}
