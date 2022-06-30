package com.example.multithreading.feature;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 吕茂陈
 * @date 2022/03/05 14:16
 */
@Slf4j
public class CompletableFutureTest {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        // get()⽅法在任务结束之前将⼀直处在阻塞状态，由于上⾯创建的 Future 没有返回，
        // 所以在这⾥调⽤ get() 将会永久性的堵塞
        String s = completableFuture.get();
        log.info("{}", s);
        completableFuture.complete("手动结束这个 future");
    }

    @Test
    public void test02() throws ExecutionException, InterruptedException {
        // runAsync 异步计算，没有返回值
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
        // supplyAsync 获取异步计算的返回结果
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            log.info("运行在一个单独的线程中");
            return "我是返回值";
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
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 使用thenApply，可能是异步线程，也可能是主线程 打印
            log.info("👍");
            return "赞";
            // 对thenApply的调用并没有阻塞程序打印log，也就是 通过回调通知机制
        }).thenApply(first -> {
            // 异步线程打印
            log.info("在看");
            return first + ", 在看";
        }).thenApply(second -> second + ", 转发");

        // 都是主线程打印
        log.info("三连有没有？");
        log.info("最后结果：{}", comboText.get());

    }


    @Test
    public void test07() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("异步线程开始");
                    return "任务A";
                })
                // 使用thenApplyAsync ，那么执行的线程是从ForkJoinPool.commonPool()中获取不同的线程进行执行
                .thenApplyAsync(first -> {
                    log.info("第一个任务：{}", first);
                    return "任务B";
                }).thenApplyAsync(second -> {
                    log.info("第二个任务：{}", second);
                    return "任务C";
                });

        log.info("先被打印出来");
        log.info("最后结果：{}", future.get());
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


    @Test
    public void test08() throws ExecutionException, InterruptedException {
        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(
                        // 模拟远端API调⽤，这⾥只返回了⼀个构造的对象
                        () -> Product.builder().id(12345L).name("颈椎/腰椎治疗仪").build())
                // 不想从回调函数中返回任何结果，那可以使⽤ thenAccept
                .thenAccept(product -> {
                    log.info("获取到远程API产品名称 " + product.getName());
                });
        voidCompletableFuture.get();
    }


    @Builder
    @Getter
    static class Product {
        long id;
        String name;
    }

    @Test
    public void test09() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            //前序操作
            log.info("前序操作");
            return "1";
        }).thenRun(() -> {
            //串⾏的后需操作，⽆参数也⽆返回值
            log.info("then 操作");
        });
        future.get();
    }
}
