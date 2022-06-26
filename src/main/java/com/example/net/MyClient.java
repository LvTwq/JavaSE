package com.example.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author 吕茂陈
 */
public class MyClient {

    public static void main(String[] args) throws IOException {
        // 和服务端建立网络连接
        Socket s = new Socket("127.0.0.1", 30000);
        // 客户端启动ClientThread线程不断地读取来自服务器的数据
        new Thread(new ClinetThread(s)).start();
        // 获取该socket对应的输出流
        PrintStream ps = new PrintStream(s.getOutputStream());
        String line;
        // 不断读取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((line = br.readLine()) != null) {
//            将键盘输入内容写入到Socket输出流
            ps.println(line);
        }
    }
}
