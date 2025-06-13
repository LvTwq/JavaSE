package com.example.tomcat.ex01.pyrmont;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static com.example.tomcat.ex01.pyrmont.HttpServer.WEB_ROOT;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/4/16 14:38
 */
@Slf4j
public class ServletProcessor1 {

    public void process(Request request, Response response) throws IOException {
        // 获取servlet名字
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);


        // 初始化URLClassLoader
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(WEB_ROOT);

            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            log.error("", e);
        } finally {
            loader.close();
        }

        // 用classLoader加载上面的servlet
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            log.error("", e);
        }

        // 将加载到的class转成Servlet，并调用service方法处理
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.getDeclaredConstructor().newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
