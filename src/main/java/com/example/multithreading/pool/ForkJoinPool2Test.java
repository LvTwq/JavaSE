package com.example.multithreading.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;

import lombok.extern.slf4j.Slf4j;

/**
 * 用数列表示为：1， 1， 2， 3， 5， 8， 13， 21
 * @author 吕茂陈
 * @date 2022/02/13 15:04
 */
@Slf4j
public class ForkJoinPool2Test {

    public static void main(String[] args) {
        int n = 20;

        // 为了追踪子线程名称，需要重写 ForkJoinWorkerThreadFactory 的方法
        final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            worker.setName("我的线程" + worker.getPoolIndex());
            return worker;
        };

        // 创建分支任务线程池，可以追踪到线程名称
        ForkJoinPool forkJoinPool = new ForkJoinPool(4, factory, null, false);
//        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 创建分治任务
        Fibonacci fibonacci = new Fibonacci(n);

        // 启动 分治任务
        Integer result = forkJoinPool.invoke(fibonacci);

        log.info("fibonacci：{}，结果是：{}", n, result);
    }

}

@Slf4j
class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // 计算的最小单元
        if (n <= 1) {
            log.info("最小：{}", n);
            return n;
        }

        Fibonacci f1 = new Fibonacci(n - 1);
        // 拆分子任务
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        // f1.join() 等待子任务执行结果
        return f2.compute() + f1.join();
    }
}
