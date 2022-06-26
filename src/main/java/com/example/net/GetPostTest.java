package com.example.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 向Web站点发送GET请求，POST请求，并从Web站点取得响应
 *
 * @author 吕茂陈
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GetPostTest {

    private final RestTemplate restTemplate;

    public static String sendGet(String url, String param) {
        String result = "";
        String urlName = url + "?" + param;

        try {
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            // 建立实际的连接
            conn.connect();
            // 获取所有的响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "---->" + map.get(key));
            }
            try (
                    // 用流读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    result += ("\n" + line);
                }
            }
        } catch (Exception e) {
            log.error("发送Get请求异常", e);
        }

        return result;
    }


    public static String sendPost(String url, String param) {
        String result = "";
        try {
            URL realURL = new URL(url);
            URLConnection conn = realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
            // 发送post请求必须设置如下
            conn.setDoOutput(true);
            conn.setDoInput(true);
            try (
                    // 获取URLConnection对象的对应输出流
                    PrintWriter out = new PrintWriter(conn.getOutputStream())
            ) {
                // 发送请求参数
                out.print(param);
                // 输出流的缓冲
                out.flush();
            }
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            ) {
                String line;
                while ((line = in.readLine()) != null) {
                    result += "\n" + line;
                }
            }

        } catch (Exception e) {
            log.error("发送Post请求失败", e);
        }

        return result;
    }

}
