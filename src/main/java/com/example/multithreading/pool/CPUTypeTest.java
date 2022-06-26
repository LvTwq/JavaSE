package com.example.multithreading.pool;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/21 09:20
 */
@Slf4j
public class CPUTypeTest implements Runnable {

    /**
     * 整体执行时间，包括在队列中等待的时间
     */
    List<Long> wholeTimeList;
    /**
     * 真正执行时间
     */
    List<Long> runTimeList;

    private long initStartTime = 0;

    /**
     * 构造函数
     *
     * @param runTimeList
     * @param wholeTimeList
     */
    public CPUTypeTest(List<Long> runTimeList, List<Long> wholeTimeList) {
        initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * 判断素数
     *
     * @param number
     * @return
     */
    public boolean isPrime(final int number) {
        if (number <= 1) {
            return false;
        }


        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 計算素数
     *
     * @return
     */
    public int countPrimes(final int lower, final int upper) {
        int total = 0;
        for (int i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                total++;
            }
        }
        return total;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        countPrimes(1, 1000000);
        long end = System.currentTimeMillis();


        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        log.info("单个线程花费时间：{}", (end - start));
    }
}
