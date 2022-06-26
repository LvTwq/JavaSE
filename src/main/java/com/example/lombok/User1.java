package com.example.lombok;

import lombok.*;
import lombok.extern.java.Log;

import java.io.*;

/**
 * @author 吕茂陈
 */
@Data
@Builder
@Log
public class User1 {

    private Integer id;
    private String name;
    private String address;

    public void test(@NonNull String s) {
        log.info("test");
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }
}
