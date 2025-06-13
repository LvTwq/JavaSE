package com.example.tomcat.ex01.pyrmont;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/4/16 11:31
 */
@Slf4j
public class HttpServer {

    /**
     * 存放静态资源的位置
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    /**
     * 关闭Server的请求
     */
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    /**
     * 是否关闭Server
     */
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            log.error("", e);
            System.exit(1);
        }

        // 循环等待一个Request请求
        while (!shutdown) {
            try (Socket socket = serverSocket.accept();
                 InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                // 封装input至request, 并处理请求
                Request request = new Request(inputStream);
                request.parse();

                // 封装output至response
                Response response = new Response(outputStream);
                response.setRequest(request);
//                response.sendStaticResource();

                // 而是如果以/servlet/开头，则委托ServletProcessor处理
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    // 原有的静态资源处理
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }


                // 关闭socket
                socket.close();

                // 如果接受的是关闭请求，则设置关闭监听request的标志
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
