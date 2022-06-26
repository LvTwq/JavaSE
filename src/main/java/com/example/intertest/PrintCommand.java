package com.example.intertest;

/**
 * @author 吕茂陈
 */
public class PrintCommand implements Command {
    @Override
    public void process(int element) {
        System.out.println("迭代输出目标数组的元素：" + element);
    }
}
