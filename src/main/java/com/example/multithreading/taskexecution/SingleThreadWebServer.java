package com.example.multithreading.taskexecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 吕茂陈
 * @date 2022/01/18 08:47
 */
public class SingleThreadWebServer {

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                Socket connection = socket.accept();
                // 处理请求
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
