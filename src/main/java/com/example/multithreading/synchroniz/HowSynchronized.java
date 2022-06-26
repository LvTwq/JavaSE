package com.example.multithreading.synchroniz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class HowSynchronized {

    private final List<Integer> data = new ArrayList<>();

    /**
     * 不涉及共享资源的慢方法
     */
    private void slow() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int wrong() {
        IntStream.rangeClosed(1, 1000).parallel()
                .forEach(i -> {
                    // 加锁粒度太粗了
                    synchronized (this) {
                        slow();
                        data.add(i);
                    }
                });
        return data.size();
    }

    public int right() {
        IntStream.rangeClosed(1, 1000).parallel()
                .forEach(i -> {
                    slow();
                    // 只对List加锁
                    synchronized (data) {
                        data.add(i);
                    }
                });
        return data.size();
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("wrong");
        new HowSynchronized().wrong();
        stopWatch.stop();

        stopWatch.start("right");
        new HowSynchronized().right();
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
    }
}
