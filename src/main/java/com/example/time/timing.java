package com.example.time;

import java.util.Date;

import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @author 吕茂陈
 * @date 2021/11/05 08:45
 */
public class timing {

    @Test
    public void test01() {
        try {
            long start = new Date().getTime();
            Thread.sleep(1000);
            System.out.println("执行耗时：" + (new Date().getTime() - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        try {
            long startTime = System.currentTimeMillis();

            // do something
            Thread.sleep(1000);

            System.out.println("执行耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopWatch1() throws InterruptedException {
        StopWatch sw = new StopWatch();

        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();

        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

        System.out.println(sw.prettyPrint());
        System.out.println(sw.getTotalTimeMillis());
        System.out.println(sw.getLastTaskName());
        System.out.println(sw.getLastTaskInfo());
        System.out.println(sw.getTaskCount());
    }
}
