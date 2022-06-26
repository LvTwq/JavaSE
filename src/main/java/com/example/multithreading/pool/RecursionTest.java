package com.example.multithreading.pool;


import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author 吕茂陈
 * @date 2022/02/15 22:11
 */
public class RecursionTest {

    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    public <T> void parallelRecursive(final Executor exec,
                                      List<Node<T>> nodes,
                                      final Collection<T> results) {
        for (Node<T> n : nodes) {
            // compute() 调用是并行执行地，每个节点的计算任务也已经放入Executor的工作队列
            exec.execute(() -> results.add(n.compute()));
            parallelRecursive(exec, n.getChildren(), results);
        }
    }

    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        try {
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultQueue;
    }

}

interface Node<T> {

    T compute();

    List<Node<T>> getChildren();
}