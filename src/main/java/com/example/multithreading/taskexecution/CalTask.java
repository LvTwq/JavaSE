package com.example.multithreading.taskexecution;

import java.util.concurrent.RecursiveTask;

/**
 * @author 吕茂陈
 */
public class CalTask extends RecursiveTask<Integer> {

    /**
     * 每个小任务最多累加20个数
     */
    private static final int THRESHOLD = 20;
    private int[] arr;
    private int start;
    private int end;

    /**
     * 累加从start到end的数组元素
     * @param arr
     * @param start
     * @param end
     */
    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            // 当end和start之差大于THRESHOLD（要累加的数 超过20个），将大任务分解成两个“小任务”
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            // 合并两个“小任务”累加的结果
            return left.join() + right.join();
        }
    }
}
