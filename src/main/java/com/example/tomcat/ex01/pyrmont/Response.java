package com.example.tomcat.ex01.pyrmont;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/4/16 11:36
 */
@Slf4j
public class Response {

    private static final int BUFFER_SIZE = 1024;

    @Setter
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }


    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        // 读取文件内容
        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }

            } catch (Exception e) {
                log.error("{}", e);
            }
        } else {
            // 文件不存在时，输出404信息
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            try {
                output.write(errorMessage.getBytes());
            } catch (IOException e) {
                log.error("{}", e);
            }
        }

    }
}

