package com.example.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 负责处理每个线程通信的线程类
 *
 * @author 吕茂陈
 */
public class ServerThread implements Runnable {

    /**
     * 定义当前线程所处理的socket
     * <p>
     * 该线程所处理的Socket对应的输出流
     */
    Socket s;
    BufferedReader br;

    public ServerThread(Socket s) throws IOException {
        this.s = s;
        // 初始化该socket对应的输入流
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content;
            // 采用循环不断地从socket中读取客户端发送来的数据
            while ((content = readFormClient()) != null) {
                // 遍历socketList中的每个Socket，将读取到的内容向每个Socket发送一次
                for (Socket s : MyServer.socketList) {
                    PrintStream ps = new PrintStream(s.getOutputStream());
                    ps.println(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFormClient() {
        try {
            return br.readLine();
            // 如果捕获异常，则表明socket对应的客户端已经关闭
        } catch (IOException e) {
            // 删除该socket
            MyServer.socketList.remove(s);
        }
        return null;
    }
}
