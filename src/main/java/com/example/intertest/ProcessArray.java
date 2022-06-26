package com.example.intertest;

/**
 * @author 吕茂陈
 */
public class ProcessArray {
    /**
     * @param target
     * @param cmd    对数组的处理行为
     */
    public void process(int[] target, Command cmd) {
        for (int t :
                target) {
            cmd.process(t);
        }
    }
}
