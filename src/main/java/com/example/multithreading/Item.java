package com.example.multithreading;

import java.util.concurrent.locks.ReentrantLock;

import lombok.Data;
import lombok.ToString;

/**
 * @author 吕茂陈
 */
@Data
public class Item {

    /**
     * 商品名
     */
    private final String name;

    /**
     * 库存剩余
     */
    int remaining = 1000;

    /**
     * ToString 不包括这个字段
     */
    @ToString.Exclude
    ReentrantLock lock = new ReentrantLock();

}
