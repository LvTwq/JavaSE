package com.example.designpatterns.decorator;

/**
 * 摩卡是一个装饰者
 * @author 吕茂陈
 */
public class Mocha extends CondimentDecorator {

    /**
     * 要让Mocha能够引用一个 Beverage：
     * 1.用一个实例变量记录饮料，也就是被装饰者
     * 2.想办法让被装饰者（饮料）被记录到实例变量中：把饮料作为构造器的参数，再由构造器将此饮料记录在实例变量中
     */
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "，摩卡";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
