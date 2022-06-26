package com.example.multithreading.taskexecution;

import java.util.concurrent.Executor;

/**
 * @author 吕茂陈
 * @date 2022/01/18 09:11
 */
public class ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}

class WithThreadExecutor implements Executor {

    /**
     * 在调用线程中，以同步的方式执行每个任务
     *
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
