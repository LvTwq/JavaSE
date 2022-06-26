package com.example.abstractlearn;

/**
 * @author 吕茂陈
 * @date 2021/12/26 13:37
 */
public abstract class AbstractGreet implements Greet {

    @Override
    public void cheer() {
        System.out.println("这里通常会做一些通用的处理,比如资源初始化,赋初值之类的");
        doCheer();
    }

    public abstract void doCheer();
}
