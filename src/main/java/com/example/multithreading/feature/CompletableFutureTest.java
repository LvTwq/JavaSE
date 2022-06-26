package com.example.multithreading.feature;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/03/05 14:16
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture();
        // get()⽅法在任务结束之前将⼀直处在阻塞状态，由于上⾯创建的 Future 没有返回，
        // 所以在这⾥调⽤ get() 将会永久性的堵塞
        String s = completableFuture.get();
        log.info("{}", s);
        completableFuture.complete("手动结束这个 future");
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        // 异步计算，没有返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("运行在一个单独的线程中！");
        });
        future.get();
    }

    @Test
    public void test03() throws ExecutionException, InterruptedException {
        // 获取异步计算的返回结果
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            log.info("运行在一个单独的线程中");
            return "我有返回值";
        });

        log.info(future.get());
    }

    /**
     * get() 方法在Future计算完成之前，会一直处于blocking状态下，
     * 对于真正的异步处理，我们希望可以通过传入回调函数，在Future结束时自动调用该回调函数
     * 这样，我们就不用等待结果
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test04() throws ExecutionException, InterruptedException {
        CompletableFuture<String> comboText = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("👍");
            return "赞";
            // 对thenApply的调用并没有阻塞程序打印log，也就是 通过回调通知机制
        }).thenApply(first -> {
            log.info("在看");
            return first + ", 在看";
        }).thenApply(second -> second + ", 转发");

        log.info("三连有没有？");
        log.info(comboText.get());
    }


    /**
     * exceptionally 就相当于 catch，出现异常，将会跳过 thenApply 的后续操作，直接捕获异常，进⾏异常处理
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test05() throws ExecutionException, InterruptedException {
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("何方神圣？");
            }
            if (age > 18) {
                return "大家都是成年人";
            } else {
                return "未成年禁止入内";
            }
        }).thenApply((str) -> {
            log.info("游戏开始！");
            return str;
        }).exceptionally(ex -> {
            log.error("必有蹊跷，来者", ex);
            return "未知！";
        });
        log.info(maturityFuture.get());
    }


    /**
     * ⽤多线程，良好的习惯是使⽤ try/finally 范式，handle 就可以起到 finally 的作⽤
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test06() throws ExecutionException, InterruptedException {
        int age = -8;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("何方神圣？");
            }
            if (age > 18) {
                return "大家都是成年人";
            } else {
                return "未成年禁止入内";
            }
        }).thenApply((str) -> {
            log.info("游戏开始！");
            return str;
        }).handle((res, ex) -> {
            if (null != ex) {
                log.error("必有蹊跷，来者", ex);
                return "未知！";
            }
            return res;
        });
        log.info(maturityFuture.get());
    }

}
