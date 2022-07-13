package com.example.invoke;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吕茂陈
 * @date 2022-07-04 17:19
 */
@Slf4j
public class Parent<T> {

    /**
     * 用于记录value更新的次数，模拟日志记录的逻辑
     */
    AtomicInteger updateCount = new AtomicInteger();

    private T value;

    @Override
    public String toString() {
        return "Parent{" +
                "updateCount=" + updateCount.get() +
                ", value=" + value +
                '}';
    }

    public void setValue(T value) {
        log.info("Parent.setValue called");
        this.value = value;
        updateCount.incrementAndGet();
    }
}

@Slf4j
class Child1 extends Parent {

    /**
     * 父类的泛型方法 setValue(T value) 在泛型擦除后是 setValue(Object value)
     * 所以子类并没有重写父类的 setValue 方法
     *
     * @param value
     */
    public void setValue(String value) {
        log.info("Child1.setValue called");
        super.setValue(value);
    }

}

@Slf4j
class Child2 extends Parent<String> {


    @Override
    public void setValue(String value) {
        log.info("Child2.setValue called");
        super.setValue(value);
    }

}