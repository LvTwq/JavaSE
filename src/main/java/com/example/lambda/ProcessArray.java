package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class ProcessArray {
    public void process(int[] target, Command cmd) {
        for (int t :
                target) {
            cmd.process(t);
        }
    }
}
