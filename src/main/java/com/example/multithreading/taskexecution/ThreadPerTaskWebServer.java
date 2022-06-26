package com.example.multithreading.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 吕茂陈
 * @date 2022/01/18 08:51
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = () -> {
                    // 处理请求
                    handleRequest(connection);
                };
                // 无限制创建线程
                new Thread(task).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket connection) {
    }
}
