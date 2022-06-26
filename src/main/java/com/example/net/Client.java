package com.example.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author 吕茂陈
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 使用构造方法连接到指定服务器
        try (Socket socket = new Socket("127.0.0.1", 30000)) {
            // 10秒后超时
            socket.setSoTimeout(10000);
            // 将Socket的输入流包装成BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 进行普通IO操作
            String s = br.readLine();
            System.out.println("来自服务器的数据：" + s);
            br.close();
        }
    }
}
