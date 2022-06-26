package com.example.intertest;

/**
 * @author 吕茂陈
 */
public class Computer {

    private Output out;

    public Computer(Output out) {
        this.out = out;
    }

    /**
     * 模拟字符串输入
     *
     * @param msg
     */
    public void keyIn(String msg) {
        out.getData(msg);
    }

    /**
     * 模拟打印
     */
    public void print() {
        out.out();
    }
}
