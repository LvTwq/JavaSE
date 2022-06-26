package com.example.decorator;

/**
 * 具体饮料实现类
 *
 * @author 吕茂陈
 */
public class Espresso extends Beverage {
    @Override
    public double cost() {
        return 1.99;
    }

    public Espresso() {
        description = "浓缩咖啡";
    }
}
