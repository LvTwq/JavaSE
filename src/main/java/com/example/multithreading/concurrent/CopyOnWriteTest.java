package com.example.multithreading.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 * @date 2022/02/12 16:41
 */
@Slf4j
public class CopyOnWriteTest {


    /**
     * 大量写的场景（10 万次 add 操作），CopyOnWriteArray 几乎比同步的 ArrayList 慢一百倍
     * 慢的原因：每次 add 时，都会用 Arrays.copyOf 创建一个新数组，频繁 add 时内存的申请释放消耗会很大
     */
    @Test
    public void testWrite() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        StopWatch stopWatch = new StopWatch();
        int loopCount = 100000;
        stopWatch.start("Write:copyOnWriteArrayList");

        // 循环100000次并发往copyOnWriteArrayList写入随机元素
        IntStream.rangeClosed(1, loopCount).parallel()
                .forEach(i -> copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(loopCount)));

        stopWatch.stop();

        stopWatch.start("Write:synchronizedList");
        //循环100000次并发往加锁的ArrayList写入随机元素
        IntStream.rangeClosed(1, loopCount).parallel()
                .forEach(e -> synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

        log.info("copyOnWriteArrayList 大小：{}", copyOnWriteArrayList.size());
        log.info("synchronizedList 大小：{}", synchronizedList.size());

    }


    public void addAll(List<Integer> list) {
        list.addAll(IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList()));
    }

    /**
     * 大量读的场景下（100 万次 get 操作），CopyOnWriteArray 又比同步的 ArrayList 快五倍以上：
     */
    @Test
    public void testRead() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        //填充数据
        addAll(copyOnWriteArrayList);
        addAll(synchronizedList);

        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        int count = copyOnWriteArrayList.size();

        stopWatch.start("Read:copyOnWriteArrayList");
        //循环1000000次并发从CopyOnWriteArrayList随机查询元素
        IntStream.rangeClosed(1, loopCount).parallel()
                .forEach(e -> copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:synchronizedList");

        //循环1000000次并发从加锁的ArrayList随机查询元素
        IntStream.range(0, loopCount).parallel()
                .forEach(e -> synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());

    }
}
