package com.example.multithreading;

import java.util.concurrent.RecursiveAction;

/**
 * @author 吕茂陈
 */
public class PrintTask extends RecursiveAction {

    /**
     * 每个小任务最多只打印50个数
     */
    private static final int THRESHOLD = 50;
    private final int start;
    private final int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        // 当end和start之间的差小于THRESHOLD时，开始打印
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "，的i值：" + i);
            }
        } else {
            // 当end和start之间的差大于THRESHOLD时，要打印的数超过50个时，将大任务分解成小任务
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
        }
    }
}
