package com.example.jsoup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 爬虫
 * @author 吕茂陈
 */
public class jsoup {

    public static void main(String[] args) throws IOException {
        String url = "https://mp.weixin.qq.com/s/veV8glZbl9memI2-9cP10A";
        Document document = Jsoup.parse(new URL(url), 10000);
        Element content = document.getElementById("js_content");
        Elements img = content.getElementsByTag("img");
        AtomicInteger id = new AtomicInteger();
        img.forEach(e -> {
            try {
                InputStream inputStream = new URL(e.attr("data-src"))
                        .openConnection().getInputStream();
                id.getAndIncrement();
                FileOutputStream outputStream = new FileOutputStream("F:\\图片\\" + id + ".jpg");
                int temp = 0;
                while ((temp = inputStream.read()) != -1) {
                    outputStream.write(temp);
                }
                System.out.println(id + ".jpg下载完毕！");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
