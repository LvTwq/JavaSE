package com.example.designpatterns;

/**
 * @author 吕茂陈
 */
public class CommandTest {

    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] target = {3, -4, 6, 4};

        // 具体处理行为取决于 PrintCommand
        pa.process(target, new PrintCommand());
        System.out.println("--------------");

        // 创建匿名内部类实例封装处理行为
        pa.process(target, new Command() {
            @Override
            public void process(int element) {
                System.out.println("吕茂陈");
            }
        });
    }
}
