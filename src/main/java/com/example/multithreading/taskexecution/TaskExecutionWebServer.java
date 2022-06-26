package com.example.multithreading.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 吕茂陈
 * @date 2022/01/18 08:59
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;

    /**
     * 固定长度为100的线程池
     */
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = () -> handleRequest(connection);
                exec.execute(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
