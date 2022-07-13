package com.example.multithreading.pool;

import com.example.multithreading.PrintTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ForkJoinPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        pool.submit(new PrintTask(0, 300));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        pool.shutdown();
    }


    @Test
    public void test01() throws InterruptedException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        // 8 并行度的线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        // 所有任务交给线程池处理
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 88)
                .parallel().forEach(i -> {
                    atomicInteger.incrementAndGet();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }));
        //提交关闭线程池申请，等待之前所有任务执行完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //查询计数器当前值
        log.info("{}", atomicInteger.get());
    }

}
