package com.example.multithreading.taskqueue;

import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/20 10:39
 */
@Slf4j
public class FifoDemo {

    /**
     * 任务队列，使用双向队列的原因是如果任务无法获取资源，还需要塞到队首，保证任务的有序性
     */
    private static final LinkedBlockingDeque<Task> TASK_QUEUE = new LinkedBlockingDeque<>();
    /**
     * 资源映射表，为了保证资源队列使用的均衡性，一旦使用完成的资源会塞到对应资源的队尾处
     */
    private static final ConcurrentHashMap<Integer, LinkedBlockingQueue<Resource>> RESOURCE_MAP
            = new ConcurrentHashMap<>();

    private static final ExecutorService TASK_POOL =
            new ThreadPoolExecutor(
                    8,
                    16,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    new CustomizableThreadFactory("TASK-THREAD-"),
                    new ThreadPoolExecutor.AbortPolicy());


    private static final ScheduledExecutorService ENGINE_POOL
            = Executors.newSingleThreadScheduledExecutor(new CustomizableThreadFactory("ENGINE-"));

    private static final AtomicInteger CODE_BUILDER = new AtomicInteger(0);


    @Data
    @Builder
    private static class Resource {
        private Integer rId;
        private Type type;
    }

    @Data
    @Builder
    private static class Task implements Runnable {
        private Integer tId;
        private Runnable work;
        private Type type;
        private Resource resource;

        @Override
        public void run() {
            log.info("[{}]任务，使用资源编号:[{}]", tId, resource.getRId());
            try {
                work.run();
            } catch (Exception exception) {
                log.error("执行任务异常", exception);
            } finally {
                log.info("[{}]任务结束，回归资源", tId);
                returnResource(resource);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    private enum Type {
        /**
         * 资源类型
         */
        A("A资源", 1),
        B("B资源", 2),
        C("C资源", 3);

        private final String desc;
        private final Integer code;

    }


    /**
     * 初始化资源队列，这里面只是简单的随机了几个资源到A、B、C三种资源，塞入各类别队列
     */
    public static void initResource() {
        Random random = new Random();
        int aCount = random.nextInt(10) + 1;
        int bCount = random.nextInt(10) + 1;
        int cCount = random.nextInt(10) + 1;
        RESOURCE_MAP.put(Type.A.getCode(), new LinkedBlockingQueue<>());
        RESOURCE_MAP.put(Type.B.getCode(), new LinkedBlockingQueue<>());
        RESOURCE_MAP.put(Type.C.getCode(), new LinkedBlockingQueue<>());
        IntStream.rangeClosed(1, aCount)
                .forEach(a -> RESOURCE_MAP.get(Type.A.getCode())
                        .add(Resource.builder().rId(a).type(Type.A).build()));
        IntStream.rangeClosed(1, bCount)
                .forEach(
                        a ->
                                RESOURCE_MAP
                                        .get(Type.B.getCode())
                                        .add(Resource.builder().rId(a).type(Type.B).build()));
        IntStream.rangeClosed(1, cCount)
                .forEach(
                        a ->
                                RESOURCE_MAP
                                        .get(Type.C.getCode())
                                        .add(Resource.builder().rId(a).type(Type.C).build()));
        log.info("初始化资源A数量:{},资源B数量:{},资源C数量:{}", aCount, bCount, cCount);

    }

    public static Resource extractResource(Type type) {
        return RESOURCE_MAP.get(type.getCode()).poll();
    }


    public static void returnResource(Resource resource) {
        log.info("开始归还资源，rId:{},资源类型:{}", resource.getRId(), resource.getType().getDesc());
        RESOURCE_MAP.get(resource.getType().code).add(resource);
        log.info("归还资源完成，rId:{},资源类型:{}", resource.getRId(), resource.getType().getDesc());
    }

    public static void enginDo() {
        /*
         * 创建一个周期执行的任务，第一次执行延期时间为initialDelay，
         * 之后每隔period执行一次，不等待第一次执行完成就开始计时
         */
        ENGINE_POOL.scheduleAtFixedRate(
                () -> {
                    Task task = TASK_QUEUE.poll();
                    if (task == null) {
                        log.info("任务队列为空，无需要执行的任务");
                    } else {
                        Resource resource = extractResource(task.getType());
                        if (resource == null) {
                            log.info("[{}]任务无法获取[{}]，返回队列", task.getTId(), task.getType().getDesc());
                            TASK_QUEUE.addFirst(task);
                        } else {
                            task.setResource(resource);
                            TASK_POOL.submit(task);
                        }
                    }
                }, 0, 1, TimeUnit.SECONDS
        );
    }

    public static void addTask(Runnable runnable, Type type) {
        Integer tId = CODE_BUILDER.incrementAndGet();
        Task task = Task.builder().tId(tId).type(type).work(runnable).build();
        log.info("提交任务[{}]到任务队列", tId);
        TASK_QUEUE.add(task);
    }

    public static void main(String[] args) {
        initResource();
        enginDo();
        Random random = new Random();
        ThreadUtil.sleep(5000);
        IntStream.range(0, 10)
                .forEach(
                        a -> addTask(() -> ThreadUtil.sleep(random.nextInt(10) + 1, TimeUnit.SECONDS), Type.A));
        IntStream.range(0, 10)
                .forEach(
                        a -> addTask(() -> ThreadUtil.sleep(random.nextInt(10) + 1, TimeUnit.SECONDS), Type.B));
        IntStream.range(0, 10)
                .forEach(
                        a -> addTask(() -> ThreadUtil.sleep(random.nextInt(10) + 1, TimeUnit.SECONDS), Type.C));

    }

}
