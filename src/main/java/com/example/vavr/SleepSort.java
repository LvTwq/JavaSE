package com.example.vavr;

import java.util.Arrays;

import io.vavr.control.Try;

public class SleepSort implements Runnable {

    private int num;

    public SleepSort(int num) {
        this.num = num;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(num * 10);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.print(num + " ");
        Try.run(() -> Thread.sleep(num * 10))
                .andThen(() -> System.out.print(num + " "));
    }

    public static void main(String[] args) {
        int[] nums = {5, 22, 10, 7, 59, 3, 16, 4, 11, 8, 14, 24, 27, 25, 26, 28, 23, 99};
        Arrays.stream(nums).forEach(n -> new Thread(new SleepSort(n)).start());
    }
}
