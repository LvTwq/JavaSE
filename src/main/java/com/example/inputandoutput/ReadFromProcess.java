package com.example.inputandoutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 吕茂陈
 */
public class ReadFromProcess {
    public static void main(String[] args) throws IOException {
        // 运行 javac 命令，返回该命令的子进程
        Process p = Runtime.getRuntime().exec("javac");
        try (
                //　以P进程的错误流创建BufferedReader对象，这个错误流对于对于本程序是输入流，对于P进程是输出流
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String buff;
            while ((buff = br.readLine()) != null) {
                System.out.println(buff);
            }
        }
    }
}
