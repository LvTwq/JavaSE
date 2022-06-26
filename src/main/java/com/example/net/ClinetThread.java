package com.example.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClinetThread implements Runnable {

    /**
     * 该线程所处理的Socket对应的输出流
     */
    BufferedReader br;

    public ClinetThread(Socket s) throws IOException {
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content;
            while ((content = br.readLine()) != null) {
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
