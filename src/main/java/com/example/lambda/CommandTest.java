package com.example.lambda;

/**
 * @author 吕茂陈
 */
public class CommandTest {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        pa.process(target, new Command() {
            @Override
            public void process(int element) {
                System.out.println(element * element);
            }
        });
    }
}
