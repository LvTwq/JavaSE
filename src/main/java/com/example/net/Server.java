package com.example.net;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 吕茂陈
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocket，用于监听客户端Socket的连接请求
        Socket s;
        try (ServerSocket ss = new ServerSocket(30000)) {
            // 每当接收到客户端Socket请求时，服务端也对应产生一个Socket
            s = ss.accept();
        }
        // 将Socket对应的输出流包装成PrintStream
        PrintStream ps = new PrintStream(s.getOutputStream());
        ps.println("你好，服务器祝你新年快乐！");
        ps.close();
        s.close();
    }
}
