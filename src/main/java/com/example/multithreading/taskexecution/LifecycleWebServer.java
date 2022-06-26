package com.example.multithreading.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author 吕茂陈
 * @date 2022/01/19 08:35
 */
public class LifecycleWebServer {

    private final ExecutorService exec = new ExecutorService() {
        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return null;
        }

        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return null;
        }

        @Override
        public Future<?> submit(Runnable task) {
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return null;
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }

        @Override
        public void execute(Runnable command) {

        }
    };

    public void start() {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (!exec.isShutdown()) {
                final Socket conn = socket.accept();
                exec.execute(() -> handleRequest(conn));
            }
        } catch (IOException | RejectedExecutionException e) {
            if (!exec.isShutdown()) {
                System.out.printf("任务提交被拒绝", e);
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    private void handleRequest(Socket connection) {
//        Request req = readRequest(connection);
//        if (isShutdownRequest(req)) {
//            stop();
//        } else {
//            dispatchRequest(req);
//        }
    }
}
