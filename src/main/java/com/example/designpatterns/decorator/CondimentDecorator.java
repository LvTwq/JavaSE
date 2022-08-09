package com.example.designpatterns.decorator;

/**
 * 装饰者类，也就是调料
 *
 * @author 吕茂陈
 */
public abstract class CondimentDecorator extends Beverage {

    /**
     * 所有调料装饰者都必须重新实现 getDescription()
     * @return
     */
    @Override
    public abstract String getDescription();


}
