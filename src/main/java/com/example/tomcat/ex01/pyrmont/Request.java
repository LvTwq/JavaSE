package com.example.tomcat.ex01.pyrmont;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/4/16 11:36
 */
@Slf4j
public class Request {

    private final InputStream inputStream;

    @Getter
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    /**
     * 处理request的方法
     */
    public void parse() {
        // 从socket中读取字符
        StringBuilder request = new StringBuilder(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            log.error("", e);
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        log.info("request: {}", request);
        // 获得两个空格之间的内容, 这里将是HttpServer.WEB_ROOT中静态文件的文件名称
        uri = parseUri(request.toString());
        log.info("uri: {}", uri);
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }

        }
        return null;
    }
}
