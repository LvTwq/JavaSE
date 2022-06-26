package com.example.multithreading;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ItemTest {

    /**
     * 商品清单
     */
    private Map<String, Item> items = new ConcurrentHashMap<>();


    public ItemTest() {
        IntStream.range(0, 10).forEach(i -> items.put("item" + i, new Item("item" + 1)));
    }

    /**
     * 创建购物车：每次从商品清单中随机选购三个商品
     *
     * @return
     */
    private List<Item> createCart() {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
                .map(name -> items.get(name)).collect(Collectors.toList());
    }


    /**
     * 下单
     *
     * @param order
     * @return
     */
    private boolean createOrder(List<Item> order) {
        // 存放所有获得的锁
        List<ReentrantLock> locks = new ArrayList<>();

        for (Item item : order) {
            try {
                // 最多等待10秒
                if (item.lock.tryLock(10, TimeUnit.SECONDS)) {
                    locks.add(item.lock);
                } else {
                    // 解锁之前获得的所有锁，返回false下单失败
                    locks.forEach(ReentrantLock::unlock);
                    return false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 锁全部拿到后执行扣减库存业务逻辑
        try {
            order.forEach(item -> item.remaining--);
        } finally {
            locks.forEach(ReentrantLock::unlock);
        }
        return true;
    }


    /**
     * jstack 查看线程信息
     */
    @Test
    public void wrong() {
        long begin = System.currentTimeMillis();
        // 并发进行100次创建购物车、下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 10000).parallel()
                .mapToObj(i -> {
                    /*
                    假设一个购物车中的商品是item1和item2，另一个购物车中的商品是item2和item1
                    一个线程先获取到了item1的锁，同时另一个线程获取到了item2的锁
                    接下来两个线程分别要获取item2和item1的锁，这时候锁已经被对方获取了，只能互相等待到10秒超时
                     */
                    List<Item> cart = createCart();
                    return createOrder(cart);
                })
                .filter(result -> result)
                .count();
        log.error("成功次数：{}，剩下商品：{}，耗时：{}毫秒，items：{}", success, items.values().stream()
                .map(item -> item.remaining).reduce(0, Integer::sum), System.currentTimeMillis() - begin, items);
    }

    @Test
    public void right() {
        long begin = System.currentTimeMillis();
        // 并发进行100次创建购物车、下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 10000).parallel()
                .mapToObj(i -> {
                    // 为购物车里的商品排序，让所有线程一定是先获取item1的锁，然后获取item2的锁
                    List<Item> cart = createCart().stream()
                            .sorted(Comparator.comparing(Item::getName))
                            .collect(Collectors.toList());
                    return createOrder(cart);
                })
                .filter(result -> result)
                .count();
        log.info("成功次数：{}，剩下商品：{}，耗时：{}毫秒，items：{}", success, items.values().stream()
                .map(item -> item.remaining).reduce(0, Integer::sum), System.currentTimeMillis() - begin, items);
    }

    @Data
    @RequiredArgsConstructor
    static class Item {

        /**
         * 商品名
         */
        final String name;

        /**
         * 库存剩余
         */
        int remaining = 1000;


        @ToString.Exclude
        ReentrantLock lock = new ReentrantLock();

    }
}

